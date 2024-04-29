package io.github.martinjelinek.golemiokotlinlib.common.entity.v2

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.BicycleCounter
import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.BicycleCounterDetection
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.BicycleCounterTemperature
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class BicycleCountersDeserializationTest {

    @Test
    fun `test deserialization of bicycle counters JSON`() {
        val json = """
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
                    "id": "camea-BC_AT-STLA",
                    "name": "Altetická",
                    "route": "A140",
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "directions": [
                      {
                        "id": "BC_AT_ST",
                        "name": "Strahov"
                      }
                    ]
                  },
                  "type": "Feature"
                }
              ]
            }
        """.trimIndent()

        val featureCollection = Json.decodeFromString<FeatureCollection<BicycleCounter>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals("Point", feature?.geometry?.type)
        assertEquals(listOf(14.4633, 50.07827), feature?.geometry?.coordinates?.value)

        val properties = feature?.properties
        assertEquals("camea-BC_AT-STLA", properties?.id)
        assertEquals("Altetická", properties?.name)
        assertEquals("A140", properties?.route)
        assertEquals("2019-05-18T07:38:37.000Z", properties?.updatedAt)
        assertEquals(1, properties?.directions?.size)

        val direction = properties?.directions?.get(0)
        assertEquals("BC_AT_ST", direction?.id)
        assertEquals("Strahov", direction?.name)
    }

    @Test
    fun `test deserialization of Measurement JSON`() {
        val json = """
            [
              {
                "id": "camea-BC_ZA-BO",
                "value": 3,
                "value_pedestrians": 7,
                "locations_id": "camea-BC_PN-VYBR",
                "measured_from": "2019-05-18T07:38:37.000Z",
                "measured_to": "2019-05-18T07:38:37.000Z",
                "measurement_count": 1
              }
            ]
        """.trimIndent()

        val detections = Json.decodeFromString<List<BicycleCounterDetection>>(json)

        assertEquals(1, detections.size)

        val detection = detections[0]
        assertEquals("camea-BC_ZA-BO", detection.id)
        assertEquals(3, detection.value)
        assertEquals(7, detection.valuePedestrians)
        assertEquals("camea-BC_PN-VYBR", detection.locationsId)
        assertEquals("2019-05-18T07:38:37.000Z", detection.measuredFrom)
        assertEquals("2019-05-18T07:38:37.000Z", detection.measuredTo)
        assertEquals(1, detection.measurementCount)
    }

    @Test
    fun `test deserialization of bicycle temperature JSON`() {
        val json = """
            [
              {
                "id": "camea-BC_AT-STLA",
                "value": 3,
                "measured_from": "2019-05-18T07:38:37.000Z",
                "measured_to": "2019-05-18T07:38:37.000Z",
                "measurement_count": 1
              }
            ]
        """.trimIndent()

        val measurements = Json.decodeFromString<List<BicycleCounterTemperature>>(json)

        assertEquals(1, measurements.size)

        val measurement = measurements[0]
        assertEquals("camea-BC_AT-STLA", measurement.id)
        assertEquals(3, measurement.value)
        assertEquals("2019-05-18T07:38:37.000Z", measurement.measuredFrom)
        assertEquals("2019-05-18T07:38:37.000Z", measurement.measuredTo)
        assertEquals(1, measurement.measurementCount)
    }
}
