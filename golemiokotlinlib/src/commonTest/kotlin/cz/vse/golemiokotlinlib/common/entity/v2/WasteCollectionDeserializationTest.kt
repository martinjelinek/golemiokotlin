package cz.vse.golemiokotlinlib.common.entity.v2

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationsPicks
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class WasteCollectionDeserializationTest {

    @Test
    fun `test deserialization of waste collections JSON`() {
        val json = """
            {
              "features": [
                {
                  "geometry": {
                    "coordinates": [
                      [
                        14.416880835710145,
                        50.089021646755796
                      ]
                    ],
                    "type": "Point"
                  },
                  "properties": {
                    "accessibility": {
                      "description": "volně",
                      "id": 1
                    },
                    "containers": [
                      {
                        "cleaning_frequency": {
                          "id": 21,
                          "duration": "P2W",
                          "frequency": 1,
                          "pick_days": "Po, St, So",
                          "next_pick": "2021-03-31"
                        },
                        "container_type": "3000 L Podzemní - SV",
                        "description": "string",
                        "trash_type": {
                          "description": "Čiré sklo",
                          "id": 7
                        },
                        "last_measurement": {
                          "measured_at_utc": "2021-08-27T14:00:32.000Z",
                          "percent_calculated": 62,
                          "prediction_utc": "2021-05-18T07:38:37.000Z"
                        },
                        "last_pick": "2021-07-09T09:00:37.000Z",
                        "ksnko_id": 15288,
                        "container_id": "15288",
                        "sensor_code": "15288",
                        "sensor_supplier": "Sensoneo",
                        "sensor_id": "Sensoneo_C00181",
                        "is_monitored": true
                      }
                    ],
                    "district": "praha-22",
                    "id": "1",
                    "name": "Přátelství 356/61",
                    "station_number": "0022/ 001",
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "is_monitored": true,
                    "ksnko_id": 3497
                  },
                  "type": "Feature"
                }
              ],
              "type": "FeatureCollection"
            }
        """.trimIndent()

        val featureCollection = Json.decodeFromString<FeatureCollection<WasteCollection>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals(1, feature?.properties?.accessibility?.id)
        assertEquals("volně", feature?.properties?.accessibility?.description)
        assertEquals("praha-22", feature?.properties?.district)
        assertEquals("1", feature?.properties?.id)
        assertEquals("Přátelství 356/61", feature?.properties?.name)
        assertEquals("0022/ 001", feature?.properties?.stationNumber)
        assertEquals("2019-05-18T07:38:37.000Z", feature?.properties?.updatedAt)
        assertEquals(true, feature?.properties?.isMonitored)
        assertEquals(3497, feature?.properties?.ksnkoId)

        val container = feature?.properties?.containers?.get(0)
        assertEquals(21, container?.cleaningFrequency?.id)
        assertEquals("P2W", container?.cleaningFrequency?.duration)
        assertEquals(1, container?.cleaningFrequency?.frequency)
        assertEquals("Po, St, So", container?.cleaningFrequency?.pickDays)
        assertEquals("2021-03-31", container?.cleaningFrequency?.nextPick)
        assertEquals("3000 L Podzemní - SV", container?.containerType)
        assertEquals("string", container?.description)
        assertEquals(7, container?.trashType?.id)
        assertEquals("Čiré sklo", container?.trashType?.description)
        assertEquals("2021-08-27T14:00:32.000Z", container?.lastMeasurement?.measuredAtUtc)
        assertEquals(62, container?.lastMeasurement?.percentCalculated)
        assertEquals("2021-05-18T07:38:37.000Z", container?.lastMeasurement?.predictionUtc)
        assertEquals("2021-07-09T09:00:37.000Z", container?.lastPick)
        assertEquals(15288, container?.ksnkoId)
        assertEquals("15288", container?.containerId)
        assertEquals("15288", container?.sensorCode)
        assertEquals("Sensoneo", container?.sensorSupplier)
        assertEquals("Sensoneo_C00181", container?.sensorId)
        assertEquals(true, container?.isMonitored)


        val geometry = feature?.geometry
        assertEquals("Point", geometry?.type)
        assertEquals(
            listOf(listOf(14.416880835710145, 50.089021646755796)),
            geometry?.coordinates?.value
        )
    }

    @Test
    fun `test deserialization of sensor data JSON`() {
        val json = """
            [
              {
                "id": "15288",
                "sensor_code": "15288",
                "percent_calculated": 67,
                "upturned": 0,
                "temperature": 15,
                "battery_status": 3.76,
                "measured_at_utc": "2019-05-17T15:11:27.000Z",
                "prediction_utc": "2019-05-18T07:38:37.000Z",
                "firealarm": 0,
                "updated_at": 1666667824020
              }
            ]
        """.trimIndent()

        val sensorDataList = Json.decodeFromString<List<WasteStationMeasurements>>(json)

        assertEquals(1, sensorDataList.size)

        val sensorData = sensorDataList[0]
        assertEquals("15288", sensorData.id)
        assertEquals("15288", sensorData.sensorCode)
        assertEquals(67, sensorData.percentCalculated)
        assertEquals(0, sensorData.upturned)
        assertEquals(15, sensorData.temperature)
        assertEquals(3.76, sensorData.batteryStatus)
        assertEquals("2019-05-17T15:11:27.000Z", sensorData.measuredAtUtc)
        assertEquals("2019-05-18T07:38:37.000Z", sensorData.predictionUtc)
        assertEquals(0, sensorData.firealarm)
        assertEquals(1666667824020, sensorData.updatedAt)
    }

    @Test
    fun `test deserialization of pick data JSON`() {
        val json = """
            [
              {
                "id": "15288",
                "pick_minfilllevel": 30,
                "decrease": 20,
                "sensor_code": "15288",
                "pick_at_utc": "2019-05-18T04:27:58.000Z",
                "percent_before": 60,
                "percent_now": 0,
                "event_driven": false,
                "updated_at": 1666667824020
              }
            ]
        """.trimIndent()

        val pickDataList = Json.decodeFromString<List<WasteStationsPicks>>(json)

        assertEquals(1, pickDataList.size)

        val pickData = pickDataList[0]
        assertEquals("15288", pickData.id)
        assertEquals(30, pickData.pickMinFillLevel)
        assertEquals(20, pickData.decrease)
        assertEquals("15288", pickData.sensorCode)
        assertEquals("2019-05-18T04:27:58.000Z", pickData.pickAtUtc)
        assertEquals(60, pickData.percentBefore)
        assertEquals(0, pickData.percentNow)
        assertEquals(false, pickData.eventDriven)
        assertEquals(1666667824020, pickData.updatedAt)
    }


    @Test
    fun `test deserialization of Ksnko data JSON`() {
        val json = """
            [
              {
                "ksnko_id": 15292,
                "sensoneo_code": "15288",
                "generated_dates": [
                  "2021-01-28"
                ]
              }
            ]
        """.trimIndent()

        val ksnkoDataList = Json.decodeFromString<List<WasteStationsPickDays>>(json)

        assertEquals(1, ksnkoDataList.size)

        val ksnkoData = ksnkoDataList[0]
        assertEquals(15292, ksnkoData.ksnkoId)
        assertEquals("15288", ksnkoData.sensoneoCode)
        assertEquals(listOf("2021-01-28"), ksnkoData.generatedDates)
    }
}
