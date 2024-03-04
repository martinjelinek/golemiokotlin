package cz.vse.golemiokotlinlib.common.entity.v2

import cz.vse.golemiokotlinlib.common.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.common.entity.featurescollection.AirQualityStationsProperties
import cz.vse.golemiokotlinlib.common.entity.featurescollection.AveragedTime
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Component
import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Measurement
import cz.vse.golemiokotlinlib.common.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.common.entity.serializers.IntAQ
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class AirQualityDeserializerTest {

    private val json = Json

    @Test
    fun `test air quality deserialization`() {
        val jsonString = """
            {
              "type": "FeatureCollection",
              "features": [
                {
                  "geometry": {
                    "type": "Point",
                    "coordinates": [
                      14.4633,
                      50.07827
                    ]
                  },
                  "properties": {
                    "id": "ACHOA",
                    "district": "praha-11",
                    "measurement": {
                      "AQ_hourly_index": 3,
                      "components": [
                        {
                          "averaged_time": {
                            "averaged_hours": 1,
                            "value": 10.7
                          },
                          "type": "NO2"
                        }
                      ]
                    },
                    "name": "Praha 4-Chodov",
                    "updated_at": "2019-05-18T07:38:37.000Z"
                  },
                  "type": "Feature"
                }
              ]
            }
        """

        // Deserialize the JSON string to FeatureCollection
        val featureCollection: FeatureCollection<AirQualityStation> =
            json.decodeFromString<FeatureCollection<AirQualityStation>>(jsonString)
        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)
        val feature = featureCollection.features?.get(0)
        assertEquals("Point", feature?.geometry?.type)
        assertEquals(listOf(14.4633, 50.07827), feature?.geometry?.coordinates?.value)
        assertEquals("ACHOA", feature?.properties?.id)
        assertEquals("praha-11", feature?.properties?.district)
        assertEquals(3, feature?.properties?.measurement?.aqHourlyIndex?.value)
    }

    @Test
    fun `deserialize FeatureCollection from JSON and verify using object`() {
        val jsonString = """
            {
              "type": "FeatureCollection",
              "features": [
                {
                  "geometry": {
                    "type": "Point",
                    "coordinates": [
                      14.4633,
                      50.07827
                    ]
                  },
                  "properties": {
                    "id": "ACHOA",
                    "district": "praha-11",
                    "measurement": {
                      "AQ_hourly_index": 3,
                      "components": [
                        {
                          "averaged_time": {
                            "averaged_hours": 1,
                            "value": 10.7
                          },
                          "type": "NO2"
                        }
                      ]
                    },
                    "name": "Praha 4-Chodov",
                    "updated_at": "2019-05-18T07:38:37.000Z"
                  },
                  "type": "Feature"
                }
              ]
            }
        """

        val actual: FeatureCollection<AirQualityStation> =
            json.decodeFromString<FeatureCollection<AirQualityStation>>(jsonString)

        val expected = FeatureCollection<AirQualityStation>(
            type = "FeatureCollection",
            features = listOf(
                AirQualityStation(
                    properties = AirQualityStationsProperties(
                        id = "ACHOA",
                        name = "Praha 4-Chodov",
                        district = "praha-11",
                        measurement = Measurement(
                            aqHourlyIndex = IntAQ(3),
                            components = listOf(
                                Component(
                                    averagedTime = AveragedTime(
                                        averagedHours = 1,
                                        value = 10.7
                                    ),
                                    type = "NO2"
                                )
                            )
                        ),
                        updatedAt = "2019-05-18T07:38:37.000Z"
                    ),
                ),
            )
        )
        assertEquals(actual, expected)
    }

    @Test
    fun `test air quality station history deserialization`() {
        val jsonString = """
            [
              {
                "id": "ACHOA",
                "measurement": {
                  "AQ_hourly_index": 3,
                  "components": [
                    {
                      "averaged_time": {
                        "averaged_hours": 1,
                        "value": 10.7
                      },
                      "type": "NO2"
                    }
                  ]
                },
                "updated_at": "2019-05-18T07:38:37.000Z"
              }
            ]
        """

        // Deserialize the JSON string into a list of AirQualityStationHistory objects
        val airQualityStationHistories = Json.decodeFromString<List<AirQualityStationHistory>>(jsonString)

        // Ensure that the deserialized list is not null and contains one element
        assertEquals(1, airQualityStationHistories.size)

        // Verify the properties of the first AirQualityStationHistory object
        val firstHistory = airQualityStationHistories[0]
        assertEquals("ACHOA", firstHistory.id)
        assertEquals(3, firstHistory.measurement?.aqHourlyIndex?.value)

        // Verify the properties of the measurement component
        val measurement = firstHistory.measurement
        assertEquals(1, measurement?.components?.size)
        assertEquals(1, measurement?.components?.get(0)?.averagedTime?.averagedHours)
        assertEquals(10.7, measurement?.components?.get(0)?.averagedTime?.value)
        assertEquals("NO2", measurement?.components?.get(0)?.type)
    }
}
