package cz.vse.golemiokotlinlib.common.entity

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Garden
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class GardensDeserializationTest {

    @Test
    fun `test deserialization of gardens JSON`() {
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
                    "address": {
                      "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko"
                    },
                    "description": "Mozartova busta v zahradě legendární usedlosti stojí na vyvýšeném místě.",
                    "district": "praha-15",
                    "id": "bertramka",
                    "image": {
                      "url": "http://www.praha.eu/public/5c/7c/31/96820_4_bertramka_08.jpg"
                    },
                    "name": "Bertramka",
                    "properties": [
                      {
                        "id": "doba",
                        "description": "Otevírací doba",
                        "value": "Celoročně duben až říjen 9-18 hod., listopad až březen 9:30-16 hod."
                      }
                    ],
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "url": "http://www.praha.eu/jnp/cz/co_delat_v_praze/parky/bertramka/bertramka_text.html"
                  },
                  "type": "Feature"
                }
              ]
            }
        """.trimIndent()

        val featureCollection = Json.decodeFromString<FeatureCollection<Garden>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals("Point", feature?.geometry?.type)
        assertEquals(listOf(14.4633, 50.07827), feature?.geometry?.coordinates?.value)

        val properties = feature?.properties
        assertEquals("bertramka", properties?.id)
        assertEquals("Bertramka", properties?.name)
        assertEquals("praha-15", properties?.district)
        assertEquals("2019-05-18T07:38:37.000Z", properties?.updatedAt)
        assertEquals(
            "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
            properties?.address?.addressFormatted
        )
        assertEquals(
            "Mozartova busta v zahradě legendární usedlosti stojí na vyvýšeném místě.",
            properties?.description
        )
        assertEquals(
            "http://www.praha.eu/public/5c/7c/31/96820_4_bertramka_08.jpg",
            properties?.image?.url
        )
        assertEquals(
            "http://www.praha.eu/jnp/cz/co_delat_v_praze/parky/bertramka/bertramka_text.html",
            properties?.url
        )

        val property = properties?.properties?.get(0)
        assertEquals("doba", property?.id)
        assertEquals("Otevírací doba", property?.description)
        assertEquals(
            "Celoročně duben až říjen 9-18 hod., listopad až březen 9:30-16 hod.",
            property?.value
        )
    }


    @Test
    fun `test deserialization of garden by id JSON`() {
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
                "address": {
                  "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko"
                },
                "description": "Mozartova busta v zahradě legendární usedlosti stojí na vyvýšeném místě.",
                "district": "praha-15",
                "id": "bertramka",
                "image": {
                  "url": "http://www.praha.eu/public/5c/7c/31/96820_4_bertramka_08.jpg"
                },
                "name": "Bertramka",
                "properties": [
                  {
                    "id": "doba",
                    "description": "Otevírací doba",
                    "value": "Celoročně duben až říjen 9-18 hod., listopad až březen 9:30-16 hod."
                  }
                ],
                "updated_at": "2019-05-18T07:38:37.000Z",
                "url": "http://www.praha.eu/jnp/cz/co_delat_v_praze/parky/bertramka/bertramka_text.html"
              },
              "type": "Feature"
            }
        """.trimIndent()

        val feature = Json.decodeFromString<Garden>(json)

        assertEquals("Feature", feature.type)
        assertEquals("Point", feature.geometry?.type)
        assertEquals(listOf(14.4633, 50.07827), feature.geometry?.coordinates?.value)
        assertEquals(
            "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
            feature.properties?.address?.addressFormatted
        )
        assertEquals(
            "Mozartova busta v zahradě legendární usedlosti stojí na vyvýšeném místě.",
            feature.properties?.description
        )
        assertEquals("praha-15", feature.properties?.district)
        assertEquals("bertramka", feature.properties?.id)
        assertEquals(
            "http://www.praha.eu/public/5c/7c/31/96820_4_bertramka_08.jpg",
            feature.properties?.image?.url
        )
        assertEquals("Bertramka", feature.properties?.name)
        assertEquals(1, feature.properties?.properties?.size)
        assertEquals("doba", feature.properties?.properties?.get(0)?.id)
        assertEquals("Otevírací doba", feature.properties?.properties?.get(0)?.description)
        assertEquals(
            "Celoročně duben až říjen 9-18 hod., listopad až březen 9:30-16 hod.",
            feature.properties?.properties?.get(0)?.value
        )
        assertEquals("2019-05-18T07:38:37.000Z", feature.properties?.updatedAt)
        assertEquals(
            "http://www.praha.eu/jnp/cz/co_delat_v_praze/parky/bertramka/bertramka_text.html",
            feature.properties?.url
        )
    }
}
