package cz.vse.golemiokotlinlib.common.entity.v2

import cz.vse.golemiokotlinlib.common.entity.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class CityDistrictsDeserializerTest {

    @Test
    fun `test deserialization of city districts JSON`() {
        val json = """
            {
              "features": [
                {
                  "geometry": {
                    "coordinates": [
                      [
                        [
                          14.50823156003554,
                          50.10498927328214
                        ],
                        [
                          14.505782430390298,
                          50.1031042405622
                        ],
                        [
                          14.509701037821998,
                          50.1029471511537
                        ],
                        [
                          14.50823156003554,
                          50.10498927328214
                        ]
                      ]
                    ],
                    "type": "Polygon"
                  },
                  "properties": {
                    "id": 43,
                    "name": "Praha 1",
                    "slug": "praha-1",
                    "updated_at": "2018-09-10T11:32:34.000Z"
                  },
                  "type": "Feature"
                }
              ],
              "type": "FeatureCollection"
            }
        """.trimIndent()

        val featureCollection = Json.decodeFromString<FeatureCollection<CityDistrict>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals(43, feature?.properties?.id)
        assertEquals("Praha 1", feature?.properties?.name)
        assertEquals("praha-1", feature?.properties?.slug)
        assertEquals("2018-09-10T11:32:34.000Z", feature?.properties?.updatedAt)
        assertEquals("Polygon", feature?.geometry?.type)
        assertEquals(
            listOf(
                listOf(
                    listOf(14.50823156003554, 50.10498927328214),
                    listOf(14.505782430390298, 50.1031042405622),
                    listOf(14.509701037821998, 50.1029471511537),
                    listOf(14.50823156003554, 50.10498927328214)
                )
            ), feature?.geometry?.coordinates?.value
        )
    }

    @Test
    fun `test deserialization of city district by id JSON`() {
        val json = """
            {
              "geometry": {
                "coordinates": [
                  [
                    [
                      14.50823156003554,
                      50.10498927328214
                    ],
                    [
                      14.505782430390298,
                      50.1031042405622
                    ],
                    [
                      14.509701037821998,
                      50.1029471511537
                    ],
                    [
                      14.50823156003554,
                      50.10498927328214
                    ]
                  ]
                ],
                "type": "Polygon"
              },
              "properties": {
                "id": 43,
                "name": "Praha 1",
                "slug": "praha-1",
                "updated_at": "2018-09-10T11:32:34.000Z"
              },
              "type": "Feature"
            }
        """.trimIndent()

        val feature = Json.decodeFromString<CityDistrict>(json)

        assertEquals("Feature", feature.type)
        assertEquals(43, feature.properties?.id)
        assertEquals("Praha 1", feature.properties?.name)
        assertEquals("praha-1", feature.properties?.slug)
        assertEquals("2018-09-10T11:32:34.000Z", feature.properties?.updatedAt)
        assertEquals("Polygon", feature.geometry?.type)
        assertEquals(
            listOf(
                listOf(
                    listOf(14.50823156003554, 50.10498927328214),
                    listOf(14.505782430390298, 50.1031042405622),
                    listOf(14.509701037821998, 50.1029471511537),
                    listOf(14.50823156003554, 50.10498927328214)
                )
            ), feature.geometry?.coordinates?.value
        )
    }
}
