package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.AirQualityClient
import cz.vse.jelinekma.pragueopendatakotlinlib.dummyData.DummyAirQualityRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Class for testing all API requests.
 */
class TestAirQualityClient : TestClient() {

    private lateinit var client: AirQualityClient
    private lateinit var dummyAirQualityRepository: DummyAirQualityRepository

    @BeforeTest
    fun setUp() {
        client = AirQualityClient(apiKey)
        dummyAirQualityRepository = DummyAirQualityRepository()
    }

    @Test
    fun testGetAllAirQualityStation() = runTest {

        val dummyData = dummyAirQualityRepository.getAllAirQualityStations(
            latlng,
            range,
            null,
            limit,
            offset,
            updatedSince
        )

        val testData = client.getAllAirQualityStations(
            latlng = latlng,
            range = range,
            districts = null,
            limit = limit,
            offset = offset,
            updatedSince = updatedSince,
        )

        assertFeatureListsEqualsExceptUpdatedAt(dummyData, testData)
    }

    @Test
    fun testGetAirHistoryData() = runTest {
        val dummyData = dummyAirQualityRepository.getAirQualityStationsHistory(
            "ACHOA",
            limit,
            offset,
            from,
            to
        )
        val testData = client.getAirQualityStationsHistory(
            sensorId = "ACHOA",
            limit = limit,
            offset = offset,
            from = from,
            to = to,
        )

        assertEquals(dummyData, testData)
    }
}
