package cz.vse.jelinekma.pragueopendatakotlinlib.v2

import cz.vse.golemiokotlinlib.v2.client.PlaygroundsClient
import cz.vse.jelinekma.pragueopendatakotlinlib.TestClient
import cz.vse.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestPlaygroundsClient : TestClient() {

    private lateinit var client: PlaygroundsClient

    @BeforeTest
    fun setUp() {
        client = PlaygroundsClient(ApiKeyLocal.API_KEY)
    }

    @Test
    fun testAllPlaygrounds() = runTest {
        val data = client.getAllPlaygrounds(
            latlng,
            range,
            null,
            limit,
            offset,
            updatedSince
        )

        assertTrue { data.isNotEmpty() }
    }

    @Test
    fun testGetPlaygroundById() = runTest {
        val data = client.getPlaygroundById("2")

        assertEquals(data.properties?.id, 2)
    }

    @Test
    fun testGetProperties() = runTest {
        val data = client.getPlaygroundsProperties()

        assertTrue { data.isNotEmpty() }
    }
}
