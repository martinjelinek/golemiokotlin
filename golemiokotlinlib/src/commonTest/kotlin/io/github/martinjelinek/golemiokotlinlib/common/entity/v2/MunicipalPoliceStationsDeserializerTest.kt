package io.github.martinjelinek.golemiokotlinlib.common.entity.v2

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MunicipalPoliceStation
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class MunicipalPoliceStationsDeserializerTest {


    @Test
    fun `test deserialization of police stations JSON`() {
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
                    "cadastral_area": "Staré Město",
                    "id": "stare-mesto-lodecka-2",
                    "note": "Přestupky",
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "district": "praha-15",
                    "address": {
                      "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
                      "street_address": "Dělnická 213/10",
                      "postal_code": "17000",
                      "address_locality": "Praha",
                      "address_region": "Holešovice",
                      "address_country": "Česko"
                    }
                  },
                  "type": "Feature"
                }
              ]
            }
        """.trimIndent()

        val featureCollection =
            Json.decodeFromString<FeatureCollection<MunicipalPoliceStation>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals(listOf(14.4633, 50.07827), feature?.geometry?.coordinates?.value)
        assertEquals("Staré Město", feature?.properties?.cadastralArea)
        assertEquals("stare-mesto-lodecka-2", feature?.properties?.id)
        assertEquals("Přestupky", feature?.properties?.note)
        assertEquals("2019-05-18T07:38:37.000Z", feature?.properties?.updatedAt)
        assertEquals("praha-15", feature?.properties?.district)
        assertEquals(
            "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
            feature?.properties?.address?.addressFormatted
        )
        assertEquals("Dělnická 213/10", feature?.properties?.address?.streetAddress)
        assertEquals("17000", feature?.properties?.address?.postalCode)
        assertEquals("Praha", feature?.properties?.address?.addressLocality)
        assertEquals("Holešovice", feature?.properties?.address?.addressRegion)
        assertEquals("Česko", feature?.properties?.address?.addressCountry)
    }

    @Test
    fun `test deserialization of police station by id JSON`() {
        val json = """
            {
              "geometry": {
                "type": "Point",
                "coordinates": [
                  14.4633,
                  50.07827
                ]
              },
              "properties": {
                "cadastral_area": "Staré Město",
                "id": "stare-mesto-lodecka-2",
                "note": "Přestupky",
                "updated_at": "2019-05-18T07:38:37.000Z",
                "district": "praha-15",
                "address": {
                  "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
                  "street_address": "Dělnická 213/10",
                  "postal_code": "17000",
                  "address_locality": "Praha",
                  "address_region": "Holešovice",
                  "address_country": "Česko"
                }
              },
              "type": "Feature"
            }
        """.trimIndent()

        val feature = Json.decodeFromString<MunicipalPoliceStation>(json)

        assertEquals("Feature", feature.type)
        assertEquals(listOf(14.4633, 50.07827), feature.geometry?.coordinates?.value)
        assertEquals("Staré Město", feature.properties?.cadastralArea)
        assertEquals("stare-mesto-lodecka-2", feature.properties?.id)
        assertEquals("Přestupky", feature.properties?.note)
        assertEquals("2019-05-18T07:38:37.000Z", feature.properties?.updatedAt)
        assertEquals("praha-15", feature.properties?.district)
        assertEquals("Dělnická 213/10, 17000 Praha-Holešovice, Česko", feature.properties?.address?.addressFormatted)
        assertEquals("Dělnická 213/10", feature.properties?.address?.streetAddress)
        assertEquals("17000", feature.properties?.address?.postalCode)
        assertEquals("Praha", feature.properties?.address?.addressLocality)
        assertEquals("Holešovice", feature.properties?.address?.addressRegion)
        assertEquals("Česko", feature.properties?.address?.addressCountry)
    }
}