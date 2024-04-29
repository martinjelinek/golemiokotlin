package io.github.martinjelinek.golemiokotlinlib.v2.client

import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalLibrariesRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache.MunicipalLibrariesCachingRepository

/**
 * Exposed client class providing municipal libraries data requests.
 */
class MunicipalLibrariesClient(apiKey: String) {

    private val repository: IMunicipalLibrariesRepository = MunicipalLibrariesCachingRepository.create(apiKey)

    /**
     * TODO doc
     */
    suspend fun getAllMunicipalLibraries(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = repository.getAllMunicipalLibraries(latlng, range, districts, limit, offset, updatedSince)

    /**
     * TODO doc
     */
    suspend fun getMunicipalLibraryById(id: String) = repository.getMunicipalLibraryById(id)
}