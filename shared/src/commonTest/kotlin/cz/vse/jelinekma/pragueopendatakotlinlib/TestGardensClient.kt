package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.GardensClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

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
        client.getAllGardens(
            latlng,
            limit = limit,
            offset = offset,
            range = range,
            districts = listOf("praha-4"),
            updatedSince = updatedSince,
        )
    }

    @Test
    fun getGardensByID() = runTest {
        client.getGardenById("letenske-sady")
    }
}
