package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MunicipalLibrary
import io.github.martinjelinek.golemiokotlinlib.common.network.GolemioApi
import io.github.martinjelinek.golemiokotlinlib.common.service.impl.cache.CachingRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalLibrariesRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote.MunicipalLibrariesRemoteRepository

internal class MunicipalLibrariesCachingRepository(
    private val remoteRepository: IMunicipalLibrariesRepository
) : IMunicipalLibrariesRepository, CachingRepository() {

    private var municipalLibraries: MutableMap<String, List<MunicipalLibrary>> =
        mutableMapOf()
    private var municipalLibrariesById: MutableMap<String, MunicipalLibrary> =
        mutableMapOf()

    override suspend fun getAllMunicipalLibraries(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalLibrary> {
        return fetchDataAndCache(
            municipalLibraries,
            latlng,
            range,
            districts,
            offset,
            updatedSince,
        ) {
            remoteRepository.getAllMunicipalLibraries(
                latlng,
                range,
                districts,
                limit,
                offset,
                updatedSince
            )
        }
    }

    override suspend fun getMunicipalLibraryById(id: String): MunicipalLibrary {
        return fetchDataAndCache(municipalLibrariesById, id) {
            remoteRepository.getMunicipalLibraryById(id)
        }
    }

    companion object Factory {

        /**
         * Creates [MunicipalLibrariesCachingRepository] over [MunicipalLibrariesRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            MunicipalLibrariesCachingRepository(
                MunicipalLibrariesRemoteRepository(
                    GolemioApi(
                        apiKey
                    )
                )
            )
    }
}
