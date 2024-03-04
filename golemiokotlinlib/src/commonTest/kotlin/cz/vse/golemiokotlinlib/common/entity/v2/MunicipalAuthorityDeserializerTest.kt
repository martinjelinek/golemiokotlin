package cz.vse.golemiokotlinlib.common.entity.v2

import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MunicipalAuthority
import cz.vse.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityQueue
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for verifying the example data from golemio
 * are being deserialized properly.
 */
class MunicipalAuthorityDeserializerTest {

    @Test
    fun `test deserialization of municipal authorities JSON`() {
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
                      "mimetype": "image/png",
                      "size": 714603,
                      "url": "https://www.mojepraha.eu/images/municipal-authorities/VJXPN4RVrTLnkih5.png"
                    },
                    "email": [
                      "posta@praha.eu"
                    ],
                    "telephone": [
                      "+420 777 7777"
                    ],
                    "web": [
                      "www.praha.eu/"
                    ],
                    "address": {
                      "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko"
                    },
                    "agendas": [
                      {
                        "keywords": [
                          "nebytové prostory"
                        ],
                        "description": "Pronájem bytových a nebytových prostor",
                        "long_description": "Ověřování shody opisu nebo kopie s listinou a ověřování pravosti podpisu"
                      }
                    ],
                    "district": "praha-15",
                    "id": "magistrat-hlavniho-mesta-prahy",
                    "name": "Magistrát hlavního města Prahy",
                    "official_board": "http://www.praha.eu/jnp/cz/o_meste/magistrat/deska/index.html",
                    "opening_hours": [
                      {
                        "closes": "12:00",
                        "opens": "08:00",
                        "day_of_week": "Monday",
                        "description": "string"
                      }
                    ],
                    "updated_at": "2019-05-18T07:38:37.000Z",
                    "type": {
                      "description": "Obecní úřad",
                      "id": "municipality"
                    }
                  },
                  "type": "Feature"
                }
              ]
            }
        """.trimIndent()

        val municipalityCollection =
            Json.decodeFromString<FeatureCollection<MunicipalAuthority>>(json)

        assertEquals("FeatureCollection", municipalityCollection.type)
        assertEquals(1, municipalityCollection.features?.size)

        val feature = municipalityCollection.features?.get(0)
        assertEquals("Feature", feature?.type)
        assertEquals(listOf(14.4633, 50.07827), feature?.geometry?.coordinates?.value)

        val properties = feature?.properties
        assertEquals("Magistrát hlavního města Prahy", properties?.name)
        assertEquals("praha-15", properties?.district)
        assertEquals("magistrat-hlavniho-mesta-prahy", properties?.id)
        assertEquals(
            "http://www.praha.eu/jnp/cz/o_meste/magistrat/deska/index.html",
            properties?.officialBoard
        )
        assertEquals("2019-05-18T07:38:37.000Z", properties?.updatedAt)
        assertEquals("Obecní úřad", properties?.type?.description)

        assertEquals(1, properties?.agendas?.size)
        assertEquals("nebytové prostory", properties?.agendas?.get(0)?.keywords?.get(0))
        assertEquals(
            "Pronájem bytových a nebytových prostor",
            properties?.agendas?.get(0)?.description
        )
        assertEquals(
            "Ověřování shody opisu nebo kopie s listinou a ověřování pravosti podpisu",
            properties?.agendas?.get(0)?.longDescription
        )

        assertEquals(1, properties?.openingHours?.size)
        assertEquals("Monday", properties?.openingHours?.get(0)?.dayOfWeek)
        assertEquals("08:00", properties?.openingHours?.get(0)?.opens)
        assertEquals("12:00", properties?.openingHours?.get(0)?.closes)

        assertEquals(1, properties?.email?.size)
        assertEquals("posta@praha.eu", properties?.email?.get(0))

        assertEquals(1, properties?.telephone?.size)
        assertEquals("+420 777 7777", properties?.telephone?.get(0))

        assertEquals(1, properties?.web?.size)
        assertEquals("www.praha.eu/", properties?.web?.get(0))

        assertEquals(
            "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
            properties?.address?.addressFormatted
        )

        val image = properties?.image
        assertEquals("image/png", image?.mimetype)
        assertEquals(714603, image?.size)
        assertEquals(
            "https://www.mojepraha.eu/images/municipal-authorities/VJXPN4RVrTLnkih5.png",
            image?.url
        )
    }

    @Test
    fun `test deserialization of Municipal Authority by id JSON`() {
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
                "image": {
                  "mimetype": "image/png",
                  "size": 714603,
                  "url": "https://www.mojepraha.eu/images/municipal-authorities/VJXPN4RVrTLnkih5.png"
                },
                "email": [
                  "posta@praha.eu"
                ],
                "telephone": [
                  "+420 777 7777"
                ],
                "web": [
                  "www.praha.eu/"
                ],
                "address": {
                  "address_formatted": "Dělnická 213/10, 17000 Praha-Holešovice, Česko"
                },
                "agendas": [
                  {
                    "keywords": [
                      "nebytové prostory"
                    ],
                    "description": "Pronájem bytových a nebytových prostor",
                    "long_description": "Ověřování shody opisu nebo kopie s listinou a ověřování pravosti podpisu"
                  }
                ],
                "district": "praha-15",
                "id": "magistrat-hlavniho-mesta-prahy",
                "name": "Magistrát hlavního města Prahy",
                "official_board": "http://www.praha.eu/jnp/cz/o_meste/magistrat/deska/index.html",
                "opening_hours": [
                  {
                    "closes": "12:00",
                    "opens": "08:00",
                    "day_of_week": "Monday",
                    "description": "string"
                  }
                ],
                "updated_at": "2019-05-18T07:38:37.000Z",
                "type": {
                  "description": "Obecní úřad",
                  "id": "municipality"
                }
              },
              "type": "Feature"
            }
        """.trimIndent()

        val municipalAuthority = Json.decodeFromString<MunicipalAuthority>(json)

        assertEquals("Feature", municipalAuthority.type)
        assertEquals(listOf(14.4633, 50.07827), municipalAuthority.geometry?.coordinates?.value)

        assertEquals("image/png", municipalAuthority.properties?.image?.mimetype)
        assertEquals(714603, municipalAuthority.properties?.image?.size)
        assertEquals(
            "https://www.mojepraha.eu/images/municipal-authorities/VJXPN4RVrTLnkih5.png",
            municipalAuthority.properties?.image?.url
        )

        assertEquals("posta@praha.eu", municipalAuthority.properties?.email?.get(0))

        assertEquals("+420 777 7777", municipalAuthority.properties?.telephone?.get(0))

        assertEquals("www.praha.eu/", municipalAuthority.properties?.web?.get(0))

        assertEquals(
            "Dělnická 213/10, 17000 Praha-Holešovice, Česko",
            municipalAuthority.properties?.address?.addressFormatted
        )

        assertEquals(1, municipalAuthority.properties?.agendas?.size)
        assertEquals(
            "nebytové prostory",
            municipalAuthority.properties?.agendas?.get(0)?.keywords?.get(0)
        )
        assertEquals(
            "Pronájem bytových a nebytových prostor",
            municipalAuthority.properties?.agendas?.get(0)?.description
        )
        assertEquals(
            "Ověřování shody opisu nebo kopie s listinou a ověřování pravosti podpisu",
            municipalAuthority.properties?.agendas?.get(0)?.longDescription
        )

        assertEquals("praha-15", municipalAuthority.properties?.district)
        assertEquals("magistrat-hlavniho-mesta-prahy", municipalAuthority.properties?.id)
        assertEquals("Magistrát hlavního města Prahy", municipalAuthority.properties?.name)
        assertEquals(
            "http://www.praha.eu/jnp/cz/o_meste/magistrat/deska/index.html",
            municipalAuthority.properties?.officialBoard
        )

        assertEquals(1, municipalAuthority.properties?.openingHours?.size)
        assertEquals("08:00", municipalAuthority.properties?.openingHours?.get(0)?.opens)
        assertEquals("12:00", municipalAuthority.properties?.openingHours?.get(0)?.closes)
        assertEquals("Monday", municipalAuthority.properties?.openingHours?.get(0)?.dayOfWeek)
        assertEquals("string", municipalAuthority.properties?.openingHours?.get(0)?.description)

        assertEquals("2019-05-18T07:38:37.000Z", municipalAuthority.properties?.updatedAt)
        assertEquals("Obecní úřad", municipalAuthority.properties?.type?.description)
        assertEquals("municipality", municipalAuthority.properties?.type?.id)
    }

    @Test
    fun `test deserialization of Client Monitoring JSON`() {
        val json = """
            {
              "last_updated": "2019+04-30T09:56:00+02:00",
              "municipal_authority_id": "skoduv-palac",
              "served_activities": [
                {
                  "activity": "OSV: CzechPoint",
                  "number_of_person_in_queue": 0,
                  "number_of_serving_counters": 5
                }
              ],
              "updated_at": "2019-04-30T09:56:00+02:00",
              "title": "Monitoring odbavování klientů ve Škodově paláci"
            }
        """.trimIndent()

        val clientMonitoring = Json.decodeFromString<MunicipalAuthorityQueue>(json)

        assertEquals("2019+04-30T09:56:00+02:00", clientMonitoring.lastUpdated)
        assertEquals("skoduv-palac", clientMonitoring.municipalAuthorityId)

        assertEquals(1, clientMonitoring.servedActivities.size)
        assertEquals("OSV: CzechPoint", clientMonitoring.servedActivities[0].activity)
        assertEquals(0, clientMonitoring.servedActivities[0].numberOfPersonInQueue)
        assertEquals(5, clientMonitoring.servedActivities[0].numberOfServingCounters)

        assertEquals("2019-04-30T09:56:00+02:00", clientMonitoring.updatedAt)
        assertEquals("Monitoring odbavování klientů ve Škodově paláci", clientMonitoring.title)
    }
}
