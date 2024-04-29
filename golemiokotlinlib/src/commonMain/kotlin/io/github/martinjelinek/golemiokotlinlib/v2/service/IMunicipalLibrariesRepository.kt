package io.github.martinjelinek.golemiokotlinlib.v2.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MunicipalLibrary

// TODO
interface IMunicipalLibrariesRepository {

    /**
     * @return A list of [MunicipalLibrary]s.
     */
    suspend fun getAllMunicipalLibraries(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MunicipalLibrary>

    /**
     * @return [MunicipalLibrary] by it's [id].
     */
    suspend fun getMunicipalLibraryById(id: String): MunicipalLibrary
}
