package cz.vse.golemiokotlinlib.common.entity

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalInstitutionTypes
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class MedicalInstitutionDeserializingTest {

    @Test
    fun `test deserialization of medical institutions JSON`() {
        val json = """
            {
              "type": "FeatureCollection",
              "features": [
                {
                  "geometry": {
                    "type": "Point",
                    "coordinates": [
                      14.392211,
                      50.094008
                    ]
                  },
                  "properties": {
                    "address": {
                      "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
                      "street_address": "Dělnická 213/10",
                      "postal_code": "17000",
                      "address_locality": "Praha",
                      "address_region": "Holešovice",
                      "address_country": "Česko"
                    },
                    "district": "praha-7",
                    "email": [
                      "praha@benu.cz"
                    ],
                    "id": "04995142000-benu-lekarna",
                    "name": "BENU Lékárna",
                    "institution_code": "4995142000",
                    "pharmacy_code": "4996100",
                    "opening_hours": [
                      {
                        "day_of_week": "Monday",
                        "opens": "09:00",
                        "closes": "12:00"
                      }
                    ],
                    "telephone": [
                      "+420 770 123 456"
                    ],
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "web": [
                      "http://www.benu.cz"
                    ],
                    "type": {
                      "id": "Z",
                      "description": "Lékárna",
                      "group": "health_care"
                    }
                  },
                  "type": "Feature"
                }
              ]
            }
        """.trimIndent()

        val featureCollection = Json.decodeFromString<FeatureCollection<MedicalInstitution>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals("Point", feature?.geometry?.type)
        assertEquals(listOf(14.392211, 50.094008), feature?.geometry?.coordinates?.value)

        val properties = feature?.properties
        assertEquals("04995142000-benu-lekarna", properties?.id)
        assertEquals("BENU Lékárna", properties?.name)
        assertEquals("praha-7", properties?.district)
        assertEquals("2019-05-18T07:38:37.000Z", properties?.updatedAt)
        assertEquals("Dělnická 213/10, 17000 Praha-Holešovice, Česko", properties?.address?.addressFormatted)
        assertEquals("Lékárna", properties?.type?.description)
        assertEquals("Z", properties?.type?.id)
    }

    @Test
    fun `test deserialization of medical institution by id JSON`() {
        val json = """
            {
              "geometry": {
                "type": "Point",
                "coordinates": [
                  14.392211,
                  50.094008
                ]
              },
              "properties": {
                "address": {
                  "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
                  "street_address": "Dělnická 213/10",
                  "postal_code": "17000",
                  "address_locality": "Praha",
                  "address_region": "Holešovice",
                  "address_country": "Česko"
                },
                "district": "praha-7",
                "email": [
                  "praha@benu.cz"
                ],
                "id": "04995142000-benu-lekarna",
                "name": "BENU Lékárna",
                "institution_code": "4995142000",
                "pharmacy_code": "4996100",
                "opening_hours": [
                  {
                    "closes": "12:00",
                    "day_of_week": "Monday",
                    "opens": "09:00"
                  }
                ],
                "telephone": [
                  "+420 770 123 456"
                ],
                "updated_at": "2019-05-18T07:38:37.000Z",
                "web": [
                  "http://www.benu.cz"
                ],
                "type": {
                  "description": "Lékárna",
                  "group": "health_care",
                  "id": "Z"
                }
              },
              "type": "Feature"
            }
        """.trimIndent()

        val feature = Json.decodeFromString<MedicalInstitution>(json)

        assertEquals("Feature", feature.type)
        assertEquals("Point", feature.geometry?.type)
        assertEquals(listOf(14.392211, 50.094008), feature.geometry?.coordinates?.value)

        val properties = feature.properties
        assertEquals("04995142000-benu-lekarna", properties?.id)
        assertEquals("BENU Lékárna", properties?.name)
        assertEquals("praha-7", properties?.district)
        assertEquals("2019-05-18T07:38:37.000Z", properties?.updatedAt)
        assertEquals("Dělnická 213/10, 17000 Praha-Holešovice, Česko", properties?.address?.addressFormatted)
        assertEquals("Lékárna", properties?.type?.description)
        assertEquals("Z", properties?.type?.id)
    }

    @Test
    fun `test deserialization of Facility JSON`() {
        val json = """
            {
              "pharmacies": [
                "Nemocniční lékárna s odbornými pracovišti",
                "Lékárna"
              ],
              "health_care": [
                "Fakultní nemocnice",
                "Nemocnice"
              ]
            }
        """.trimIndent()

        val facility = Json.decodeFromString<MedicalInstitutionTypes>(json)

        facility.pharmacies?.let { assertEquals(2, it.size) }
        facility.healthCare?.let { assertEquals(2, it.size) }

        assertEquals("Nemocniční lékárna s odbornými pracovišti", facility.pharmacies?.get(0))
        assertEquals("Lékárna", facility.pharmacies?.get(1))
        assertEquals("Fakultní nemocnice", facility.healthCare?.get(0))
        assertEquals("Nemocnice", facility.healthCare?.get(1))
    }
}
