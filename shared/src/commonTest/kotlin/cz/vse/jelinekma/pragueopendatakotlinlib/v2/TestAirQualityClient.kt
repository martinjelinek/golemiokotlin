package cz.vse.jelinekma.pragueopendatakotlinlib.v2

import cz.vse.golemiokotlinlib.v2.client.AirQualityClient
import cz.vse.golemiokotlinlib.common.entity.featurescollection.AveragedTime
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Component
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Measurement
import cz.vse.golemiokotlinlib.common.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.common.entity.serializers.StringAQ
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
        assertEquals(getHistoryDummyData(), testData)
    }

    private fun getHistoryDummyData(): List<AirQualityStationHistory> {
        return listOf(
            AirQualityStationHistory(
                "ACHOA",
                Measurement(
                    StringAQ("2B"),
                    listOf(
                        Component(AveragedTime(3, 12.8), "NO2"),
                        Component(AveragedTime(3, 23.4), "PM10")
                    )
                )
            ),
            AirQualityStationHistory(
                "ACHOA",
                Measurement(
                    StringAQ("2B"),
                    listOf(
                        Component(AveragedTime(3, 9.8), "NO2"),
                        Component(AveragedTime(3, 18.2), "PM10")
                    )
                )
            )
        )
    }
}
