package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal

/**
 * Common logic for testing the clients.
 */
open class TestClient {
    val apiKey: String = ApiKeyLocal.API_KEY
    val latlng = Pair("50.124935", "14.457204")
    val from = "2023-05-16T04:27:58.000Z"
    val to = "2023-05-17T04:27:58.000Z"
    val updatedSince = "2023-05-18T07:38:37.000Z"
    val range = 5000
    val limit = 2
    val offset = 2
}
