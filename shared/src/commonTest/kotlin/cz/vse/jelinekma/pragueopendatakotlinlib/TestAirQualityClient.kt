package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.AirQualityClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

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
        client.getAllAirQualityStations(
            latlng = latlng,
            range = range,
            districts = null,
            limit = limit,
            offset = offset,
            updatedSince = updatedSince,
        )
    }

    @Test
    fun testGetAirHistoryData() = runTest {
        client.getAirQualityStationsHistory(
            sensorId = "ACHOA",
            limit = limit,
            offset = offset,
            from = from,
            to = to,
        )
    }
}
