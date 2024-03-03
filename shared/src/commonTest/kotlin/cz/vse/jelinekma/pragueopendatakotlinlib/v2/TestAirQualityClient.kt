package cz.vse.jelinekma.pragueopendatakotlinlib.v2

import cz.vse.golemiokotlinlib.v2.client.AirQualityClient
import cz.vse.jelinekma.pragueopendatakotlinlib.TestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Class for testing all API requests.
 */
class TestAirQualityClient : TestClient() {

    private lateinit var client: AirQualityClient

    @BeforeTest
    fun setUp() {
        client = AirQualityClient(apiKey)
    }

    @Test
    fun testGetAllAirQualityStation() = runTest {
        val testData = client.getAllAirQualityStations(
            latlng = latlng,
            range = range,
            districts = null,
            limit = limit,
            offset = offset,
            updatedSince = updatedSince,
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun testGetAirHistoryData() = runTest {

        val testData = client.getAirQualityStationsHistory(
            sensorId = "ACHOA",
            limit = limit,
            offset = offset,
            from = from,
            to = to,
        )

        // due to realtime update-at non testable as whole
        assertEquals(testData.first().id, "ACHOA")
    }
}
