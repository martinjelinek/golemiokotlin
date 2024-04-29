package io.github.martinjelinek.golemiokotlinlib.v2.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.Garden

interface IGardensRepository {

    /**
     * @return A list of [Garden]s.
     */
    suspend fun getAllGardens(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Garden>

    /**
     * @return [Garden] by it's [id].
     */
    suspend fun getGardenById(
        id: String
    ): Garden
}
