package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.Garden
import io.github.martinjelinek.golemiokotlinlib.common.network.GolemioApi
import io.github.martinjelinek.golemiokotlinlib.common.service.impl.cache.CachingRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.IGardensRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote.GardensRemoteRepository

/**
 * Repository for caching gardens data requests.
 * Non-persistent cache using kotlin collections.
 */
class GardensCachingRepository(
    private val remoteRepository: IGardensRepository,
) : IGardensRepository, CachingRepository() {

    private var allGardensCache: MutableMap<String, List<Garden>> = mutableMapOf()
    private var gardensByIdCache: MutableMap<String, Garden> = mutableMapOf()

    override suspend fun getAllGardens(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Garden> {
        return fetchDataAndCache(
            allGardensCache,
            latlng, range, districts, limit, offset, updatedSince,
        ) {
            remoteRepository.getAllGardens(
                latlng, range, districts, limit, offset, updatedSince,
            )
        }
    }

    override suspend fun getGardenById(id: String): Garden {
        return fetchDataAndCache(
            gardensByIdCache,
            id,
        ) {
            remoteRepository.getGardenById(id)
        }
    }

    companion object Factory {

        /**
         * Creates [GardensCachingRepository] over [GardensRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            GardensCachingRepository(GardensRemoteRepository(GolemioApi(apiKey)))
    }
}
