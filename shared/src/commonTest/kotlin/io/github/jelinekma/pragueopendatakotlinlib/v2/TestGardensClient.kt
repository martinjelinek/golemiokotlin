package io.github.jelinekma.pragueopendatakotlinlib.v2

import io.github.martinjelinek.golemiokotlinlib.v2.client.GardensClient
import io.github.jelinekma.pragueopendatakotlinlib.TestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test class for garden client.
 */
class TestGardensClient : TestClient() {

    private lateinit var client: GardensClient

    @BeforeTest
    fun setUp() {
        client = GardensClient(apiKey)
    }

    @Test
    fun testGetAllGardens() = runTest {
        val testData = client.getAllGardens(
            latlng,
            limit = limit,
            offset = 0,
            range = 10000,
            districts = listOf("praha-7"),
            updatedSince = updatedSince,
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun getGardensByID() = runTest {
        val testData = client.getGardenById("letenske-sady")

        assertTrue { testData.properties?.name is String }
        assertEquals(testData.properties?.name, "Letenské sady")
    }
}
