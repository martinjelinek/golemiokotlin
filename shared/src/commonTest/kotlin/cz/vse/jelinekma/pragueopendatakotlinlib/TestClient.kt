package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Feature
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

    /**
     * @return whether [list1] and [list2] are equals.
     * Excludes update_at param from both because those are always different.
     */
    fun <T: Feature> assertFeatureListsEqualsExceptUpdatedAt(list1: List<T>, list2: List<T>): Boolean {
        if (list1.size != list2.size) {
            return false
        }

        for (i in list1.indices) {
            val station1 = list1[i]
            val station2 = list2[i]

            if (station1.properties != station2.properties) {
                return false
            }
        }

        return true
    }
}
