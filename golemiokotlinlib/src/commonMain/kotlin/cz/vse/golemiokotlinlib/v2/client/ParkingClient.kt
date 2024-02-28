package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.v2.service.IParkingRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.ParkingCachingRepository

/**
 * Exposed client class providing bicycle counters data requests.
 */
class ParkingClient(apiKey: String) {

    private val repository: IParkingRepository = ParkingCachingRepository.create(apiKey)

    /**
     * TODO doc
     */
    suspend fun getAllParkings(
        latlng: Pair<String, String>?,
        range: Int?,
        source: String?,
        sourceId: String?,
        category: List<String>,
        limit: Int?,
        offset: Int?,
        minutesBefore: Int?,
        updatedSince: String?
    ) = repository.getAllParkings(
        latlng,
        range,
        source,
        sourceId,
        category,
        limit,
        offset,
        minutesBefore,
        updatedSince
    )

    // TODO the rest of methods
}
