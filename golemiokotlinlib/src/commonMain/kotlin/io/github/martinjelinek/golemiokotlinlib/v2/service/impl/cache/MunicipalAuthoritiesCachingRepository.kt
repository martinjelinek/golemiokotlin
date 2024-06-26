package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MunicipalAuthority
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityQueue
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityType
import io.github.martinjelinek.golemiokotlinlib.common.network.GolemioApi
import io.github.martinjelinek.golemiokotlinlib.common.service.impl.cache.CachingRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalAuthoritiesRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote.MunicipalAuthoritiesRemoteRepository

internal class MunicipalAuthoritiesCachingRepository(
    private val remoteRepository: IMunicipalAuthoritiesRepository
) : IMunicipalAuthoritiesRepository, CachingRepository() {

    private var municipalAuthorities: MutableMap<String, List<MunicipalAuthority>> =
        mutableMapOf()
    private var municipalAuthoritiesById: MutableMap<String, MunicipalAuthority> =
        mutableMapOf()
    private var municipalAuthorityQueuesById: MutableMap<String, MunicipalAuthorityQueue> =
        mutableMapOf()

    override suspend fun getAllMunicipalAuthorities(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalAuthority> {
        return fetchDataAndCache(
            municipalAuthorities,
            latlng, range, districts, type, limit, offset, updatedSince
        ) {
            remoteRepository.getAllMunicipalAuthorities(
                latlng,
                range,
                districts,
                type,
                limit,
                offset,
                updatedSince
            )
        }
    }

    override suspend fun getMunicipalAuthorityById(id: String): MunicipalAuthority {
        return fetchDataAndCache(municipalAuthoritiesById, id) {
            remoteRepository.getMunicipalAuthorityById(id)
        }
    }

    override suspend fun getMunicipalAuthoritiesQueues(id: String): MunicipalAuthorityQueue {
        return fetchDataAndCache(municipalAuthorityQueuesById, id) {
            remoteRepository.getMunicipalAuthoritiesQueues(id)
        }
    }

    companion object Factory {

        /**
         * Creates [MunicipalAuthoritiesCachingRepository] over [MunicipalAuthoritiesRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            MunicipalAuthoritiesCachingRepository(MunicipalAuthoritiesRemoteRepository(GolemioApi(apiKey)))
    }
}
