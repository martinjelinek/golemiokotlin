package cz.vse.golemiokotlinlib.common.entity.v2

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Playground
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class PlaygroundsDeserializerTest {

    @Test
    fun `test deserialization of playgrounds JSON`() {
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
                    "image": {
                      "url": "http://www.hristepraha.cz/images/img/2d73f6832f5ce39fc5987d27d9f4541fo.jpg"
                    },
                    "content": "Popis: Asi 300 m od prodejny Lidl v Hornoměcholupské ulici se na okraji louky rozkládá první hřiště (43.A). Tvoří ho...",
                    "id": 72,
                    "name": "Hostivařský lesopark (východní část) - hřiště 43.B",
                    "perex": "Trasa je vhodným polodenním rodinným výletem.",
                    "properties": [
                      {
                        "id": 6,
                        "description": "WC na hřišti nebo v bezprostřední blízkosti"
                      }
                    ],
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "url": "http://www.hristepraha.cz/hriste/mapa/hostivarsky-lesopark-vychodni-cast-hriste-43-b",
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

        val featureCollection = Json.decodeFromString<FeatureCollection<Playground>>(json)

        assertEquals("FeatureCollection", featureCollection.type)
        assertEquals(1, featureCollection.features?.size)

        val feature = featureCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals(listOf(14.4633, 50.07827), feature?.geometry?.coordinates?.value)
        assertEquals("http://www.hristepraha.cz/images/img/2d73f6832f5ce39fc5987d27d9f4541fo.jpg", feature?.properties?.image?.url)
        assertEquals("Popis: Asi 300 m od prodejny Lidl v Hornoměcholupské ulici se na okraji louky rozkládá první hřiště (43.A). Tvoří ho...", feature?.properties?.content)
        assertEquals(72, feature?.properties?.id)
        assertEquals("Hostivařský lesopark (východní část) - hřiště 43.B", feature?.properties?.name)
        assertEquals("Trasa je vhodným polodenním rodinným výletem.", feature?.properties?.perex)
        assertEquals("2019-05-18T07:38:37.000Z", feature?.properties?.updatedAt)
        assertEquals("http://www.hristepraha.cz/hriste/mapa/hostivarsky-lesopark-vychodni-cast-hriste-43-b", feature?.properties?.url)
        assertEquals("praha-15", feature?.properties?.district)
        assertEquals("Dělnická 213/10, 17000 Praha-Holešovice, Česko", feature?.properties?.address?.addressFormatted)
        assertEquals("Dělnická 213/10", feature?.properties?.address?.streetAddress)
        assertEquals("17000", feature?.properties?.address?.postalCode)
        assertEquals("Praha", feature?.properties?.address?.addressLocality)
        assertEquals("Holešovice", feature?.properties?.address?.addressRegion)
        assertEquals("Česko", feature?.properties?.address?.addressCountry)
    }
}