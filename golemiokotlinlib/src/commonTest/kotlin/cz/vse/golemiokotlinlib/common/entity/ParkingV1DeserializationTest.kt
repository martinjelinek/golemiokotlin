package cz.vse.golemiokotlinlib.common.entity

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.common.entity.responsedata.ParkingsV1History
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class ParkingV1DeserializationTest {

    @Test
    fun `test parking v1 deserialization`() {
        val jsonString = """
            {
              "features": [
                {
                  "geometry": {
                    "coordinates": [
                      14.441252,
                      50.109318
                    ],
                    "type": "Point"
                  },
                  "properties": {
                    "id": "tsk-143",
                    "name": "Holešovice",
                    "parking_type": {
                      "id": 1,
                      "description": "P+R parkoviště"
                    },
                    "num_of_free_places": 2,
                    "num_of_taken_places": 63,
                    "updated_at": "2020-05-18T07:38:37.000Z",
                    "total_num_of_places": 65,
                    "average_occupancy": {
                      "0": {
                        "0": 11,
                        "1": 12,
                        "2": 12,
                        "3": 13,
                        "4": 13,
                        "5": 22,
                        "6": 26,
                        "7": 35,
                        "8": 36,
                        "9": 51,
                        "10": 51,
                        "11": 51,
                        "12": 51,
                        "13": 51,
                        "14": 51,
                        "15": 51,
                        "16": 51,
                        "17": 48,
                        "18": 46,
                        "19": 29,
                        "20": 28,
                        "21": 29,
                        "22": 18,
                        "23": 16
                      }
                    },
                    "district": "praha-10",
                    "address": {
                      "address_country": "Česko",
                      "address_formatted": "Vrbenského, 17000 Praha Holešovice, Česko",
                      "address_locality": "Praha",
                      "address_region": "Holešovice",
                      "postal_code": "17000",
                      "street_address": "Vrbenského"
                    },
                    "last_updated": 1556868553000,
                    "payment_link": "https://ke-utc.appspot.com/static/onstreet.html?shortname=143",
                    "payment_shortname": "143"
                  },
                  "type": "Feature"
                }
              ],
              "type": "FeatureCollection"
            }
        """

        // Deserialize the JSON string into a FeatureCollection object
        val featureCollection = Json.decodeFromString<FeatureCollection<ParkingV1>>(jsonString)

        // Ensure that the deserialized FeatureCollection is not null
        assertEquals(1, featureCollection.features?.size)

        // Verify the properties of the first Feature
        val firstFeature = featureCollection.features?.get(0)
        assertEquals("tsk-143", firstFeature?.properties?.id?.value)
        assertEquals("Holešovice", firstFeature?.properties?.name)
        assertEquals(2, firstFeature?.properties?.numOfFreePlaces)
        assertEquals(63, firstFeature?.properties?.numOfTakenPlaces)
        assertEquals("2020-05-18T07:38:37.000Z", firstFeature?.properties?.updatedAt)
        assertEquals(65, firstFeature?.properties?.totalNumOfPlaces)
        assertEquals("praha-10", firstFeature?.properties?.district)

        // Verify the geometry coordinates of the first Feature
        assertEquals(listOf(14.441252, 50.109318), firstFeature?.geometry?.coordinates?.value)
    }

    @Test
    fun `test parking v1 by id deserialization`() {
        // JSON string representing the Feature
        val jsonString = """
            {
              "geometry": {
                "coordinates": [
                  14.441252,
                  50.109318
                ],
                "type": "Point"
              },
              "properties": {
                "id": "tsk-143",
                "name": "Holešovice",
                "parking_type": {
                  "id": 1,
                  "description": "P+R parkoviště"
                },
                "num_of_free_places": 2,
                "num_of_taken_places": 63,
                "updated_at": "2020-05-18T07:38:37.000Z",
                "total_num_of_places": 65,
                "average_occupancy": {
                  "0": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  },
                  "1": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  },
                  "2": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  },
                  "3": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  },
                  "4": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  },
                  "5": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  },
                  "6": {
                    "0": 11,
                    "1": 12,
                    "2": 12,
                    "3": 13,
                    "4": 13,
                    "5": 22,
                    "6": 26,
                    "7": 35,
                    "8": 36,
                    "9": 51,
                    "10": 51,
                    "11": 51,
                    "12": 51,
                    "13": 51,
                    "14": 51,
                    "15": 51,
                    "16": 51,
                    "17": 48,
                    "18": 46,
                    "19": 29,
                    "20": 28,
                    "21": 29,
                    "22": 18,
                    "23": 16
                  }
                },
                "district": "praha-10",
                "address": {
                  "address_country": "Česko",
                  "address_formatted": "Vrbenského, 17000 Praha Holešovice, Česko",
                  "address_locality": "Praha",
                  "address_region": "Holešovice",
                  "postal_code": "17000",
                  "street_address": "Vrbenského"
                },
                "last_updated": 1556868553000,
                "payment_link": "https://ke-utc.appspot.com/static/onstreet.html?shortname=143",
                "payment_shortname": "143"
              },
              "type": "Feature"
            }
        """

        val feature = Json.decodeFromString<ParkingV1>(jsonString)

        assertEquals("Feature", feature.type)
        assertEquals("tsk-143", feature.properties?.id?.value)
        assertEquals("Holešovice", feature.properties?.name)
        assertEquals("praha-10", feature.properties?.district)
        assertEquals(2, feature.properties?.numOfFreePlaces)
        assertEquals(63, feature.properties?.numOfTakenPlaces)
        assertEquals("2020-05-18T07:38:37.000Z", feature.properties?.updatedAt)
        assertEquals(65, feature.properties?.totalNumOfPlaces)
        assertEquals("https://ke-utc.appspot.com/static/onstreet.html?shortname=143", feature.properties?.paymentLink)
        assertEquals("143", feature.properties?.paymentShortname)

        assertEquals(listOf(14.441252, 50.109318), feature.geometry?.coordinates?.value)

        val averageOccupancy = feature.properties?.averageOccupancy
        assertEquals(11, averageOccupancy?.get("0")?.get("0"))
        assertEquals(22, averageOccupancy?.get("5")?.get("5"))
        assertEquals(16, averageOccupancy?.get("6")?.get("23"))
    }

    @Test
    fun `test deserialization of parking history JSON array`() {
        // Given
        val json = """
            [
              {
                "id": "tsk-143",
                "num_of_free_places": 2,
                "num_of_taken_places": 63,
                "updated_at": "2020-05-18T07:38:37.000Z",
                "total_num_of_places": 65,
                "last_updated": 1556868553000
              }
            ]
        """.trimIndent()

        // When
        val parkingHistory: List<ParkingsV1History> = Json.decodeFromString(json)

        // Then
        assertEquals(1, parkingHistory.size)

        val entry = parkingHistory[0]
        assertEquals("tsk-143", entry.id)
        assertEquals(2, entry.numOfFreePlaces)
        assertEquals(63, entry.numOfTakenPlaces)
        assertEquals("2020-05-18T07:38:37.000Z", entry.updatedAt)
        assertEquals(65, entry.totalNumOfPlaces)
        assertEquals(1556868553000, entry.lastUpdated)
    }
}
