package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.common.entity.featurescollection.Garden
import cz.vse.golemiokotlinlib.v2.service.IGardensRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.GardensCachingRepository

/**
 * Exposed client class providing gardens data requests.
 */
class GardensClient(apiKey: String) {

    private val repository: IGardensRepository = GardensCachingRepository.create(apiKey)

    /**
     * @return list of [Garden]s.
     */
    suspend fun getAllGardens(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = repository.getAllGardens(latlng, range, districts, limit, offset, updatedSince)

    /**
     * @return [Garden] with [id].
     */
    suspend fun getGardenById(id: String) = repository.getGardenById(id)
}
