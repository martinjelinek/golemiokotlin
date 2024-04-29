package io.github.martinjelinek.golemiokotlinlib.v2.client

import io.github.martinjelinek.golemiokotlinlib.v2.service.IPlaygroundsRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache.PlaygroundsCachingRepository

/**
 * Exposed client class providing bicycle counters data requests.
 */
class PlaygroundsClient(apiKey: String) {

    private val repository: IPlaygroundsRepository = PlaygroundsCachingRepository.create(apiKey)

    /**
     * TODO doc
     */
    suspend fun getAllPlaygrounds(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = repository.getAllPlaygrounds(latlng, range, districts, limit, offset, updatedSince)

    /**
     * TODO doc
     */
    suspend fun getPlaygroundById(id: String) = repository.getPlaygroundById(id)

    /**
     * TODO doc
     */
    suspend fun getPlaygroundsProperties() = repository.getPlaygroundsProperties()
}
