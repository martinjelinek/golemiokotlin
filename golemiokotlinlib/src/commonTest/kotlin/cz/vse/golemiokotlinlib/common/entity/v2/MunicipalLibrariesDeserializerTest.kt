package cz.vse.golemiokotlinlib.common.entity.v2

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MunicipalLibrary
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class MunicipalLibrariesDeserializerTest {

    @Test
    fun `test deserialization of FeatureCollection JSON`() {
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
                      "address_formatted": "U Brusnice 324/9, 16900 Praha 6 - Hradčany, Česko",
                      "street_address": "U Brusnice 324/9",
                      "postal_code": "16900",
                      "address_locality": "Praha 6 - Hradčany",
                      "address_region": "Hradčany",
                      "address_country": "Česko"
                    },
                    "email": "knihovna@mlp.cz",
                    "id": 68,
                    "name": "Sběrný dvůr hlavního města Prahy Proboštská",
                    "opening_hours": [
                      {
                        "closes": "12:00",
                        "day_of_week": "Monday",
                        "description": "Základní provozní doba",
                        "is_default": true,
                        "opens": "09:00",
                        "valid_from": "2021-12-30T23:00:00.000Z",
                        "valid_through": "2022-01-01T23:00:00.000Z"
                      }
                    ],
                    "services": [
                      {
                        "description": "Bezbariérový přístup pro vozíčkáře i rodiče s kočárky",
                        "id": 1,
                        "name": "Bezbariérový přístup"
                      }
                    ],
                    "telephone": "+420 770 130 297",
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "web": "https://www.mlp.cz/cz/pobocky/brusnice-vzacne-tisky/",
                    "district": "praha-6"
                  },
                  "type": "Feature"
                }
              ]
            }
        """.trimIndent()

        val featureCollection = Json.decodeFromString<FeatureCollection<MunicipalLibrary>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals(68, feature?.properties?.id)
        assertEquals("Sběrný dvůr hlavního města Prahy Proboštská", feature?.properties?.name)
        assertEquals("praha-6", feature?.properties?.district)
        assertEquals("knihovna@mlp.cz", feature?.properties?.email)
        assertEquals("+420 770 130 297", feature?.properties?.telephone)
        assertEquals("2019-05-18T07:38:37.000Z", feature?.properties?.updatedAt)
        assertEquals(
            "https://www.mlp.cz/cz/pobocky/brusnice-vzacne-tisky/",
            feature?.properties?.web
        )
        assertEquals(1, feature?.properties?.services?.size)
        assertEquals("Bezbariérový přístup", feature?.properties?.services?.get(0)?.name)

        assertEquals(1, feature?.properties?.openingHours?.size)
        assertEquals(
            "Základní provozní doba",
            feature?.properties?.openingHours?.get(0)?.description
        )
        assertEquals("09:00", feature?.properties?.openingHours?.get(0)?.opens)
        assertEquals("12:00", feature?.properties?.openingHours?.get(0)?.closes)


        assertEquals(
            "2021-12-30T23:00:00.000Z",
            feature?.properties?.openingHours?.get(0)?.validFrom
        )
        assertEquals(
            "2022-01-01T23:00:00.000Z",
            feature?.properties?.openingHours?.get(0)?.validThrough
        )
    }

    @Test
    fun `test deserialization of Feature JSON`() {
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
                  "address_formatted": "U Brusnice 324/9, 16900 Praha 6 - Hradčany, Česko",
                  "street_address": "U Brusnice 324/9",
                  "postal_code": "16900",
                  "address_locality": "Praha 6 - Hradčany",
                  "address_region": "Hradčany",
                  "address_country": "Česko"
                },
                "email": "knihovna@mlp.cz",
                "id": 68,
                "name": "Sběrný dvůr hlavního města Prahy Proboštská",
                "opening_hours": [
                  {
                    "closes": "12:00",
                    "day_of_week": "Monday",
                    "description": "Základní provozní doba",
                    "is_default": true,
                    "opens": "09:00",
                    "valid_from": "2021-12-30T23:00:00.000Z",
                    "valid_through": "2022-01-01T23:00:00.000Z"
                  }
                ],
                "services": [
                  {
                    "description": "Bezbariérový přístup pro vozíčkáře i rodiče s kočárky",
                    "id": 1,
                    "name": "Bezbariérový přístup"
                  }
                ],
                "telephone": "+420 770 130 297",
                "updated_at": "2019-05-18T07:38:37.000Z",
                "web": "https://www.mlp.cz/cz/pobocky/brusnice-vzacne-tisky/",
                "district": "praha-6"
              },
              "type": "Feature"
            }
        """.trimIndent()

        val feature = Json.decodeFromString<MunicipalLibrary>(json)

        assertEquals("Feature", feature.type)
        assertEquals(listOf(14.392211, 50.094008), feature.geometry?.coordinates?.value)
        assertEquals(
            "U Brusnice 324/9, 16900 Praha 6 - Hradčany, Česko",
            feature.properties?.address?.addressFormatted
        )
        assertEquals("knihovna@mlp.cz", feature.properties?.email)
        assertEquals(68, feature.properties?.id)
        assertEquals("Sběrný dvůr hlavního města Prahy Proboštská", feature.properties?.name)
        assertEquals("+420 770 130 297", feature.properties?.telephone)
        assertEquals("2019-05-18T07:38:37.000Z", feature.properties?.updatedAt)
        assertEquals("https://www.mlp.cz/cz/pobocky/brusnice-vzacne-tisky/", feature.properties?.web)
        assertEquals("praha-6", feature.properties?.district)
        assertEquals("Point", feature.geometry?.type)
    }
}
