package io.github.martinjelinek.golemiokotlinlib.v2.client

import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalPoliceStationsRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache.MunicipalPoliceStationsCachingRepository

/**
 * Exposed client class providing municipal police stations data requests.
 */
class MunicipalPoliceStationsClient(apiKey: String) {

    private val repository: IMunicipalPoliceStationsRepository = MunicipalPoliceStationsCachingRepository.create(apiKey)

    /**
     * TODO doc
     */
    suspend fun getAllMunicipalPoliceStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = repository.getAllMunicipalPoliceStations(latlng, range, districts, limit, offset, updatedSince)

    /**
     * TODO doc
     */
    suspend fun getMunicipalPoliceStationById(id: String) = repository.getMunicipalPoliceStationById(id)
}
