package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.service.ISortedWasteStationsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.SortedWasteStationsCachingRepository

/**
 * Exposed client class providing bicycle counters data requests.
 */
class SortedWasteStationsClient(apiKey: String) {

    private val repository: ISortedWasteStationsRepository = SortedWasteStationsCachingRepository.create(apiKey)

    /**
     * TODO doc
     */
    suspend fun getSortedWasteStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        accessibility: WasteStationAccessibility?,
        limit: Int?,
        offset: Int?,
        onlyMonitored: Boolean?,
        id: String,
        ksnkoId: String
    ) = repository.getSortedWasteStations(
        latlng,
        range,
        districts,
        accessibility,
        limit,
        offset,
        onlyMonitored,
        id,
        ksnkoId,
    )

    /**
     * TODO doc
     */
    suspend fun getSortedWasteStationsMeasurements(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ) = repository.getSortedWasteStationsMeasurements(
        containerId, ksnkoId, limit, offset, from, to,
    )

    /**
     * TODO doc
     */
    suspend fun getSortedWasteStationsPicks(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ) = repository.getSortedWasteStationsPicks(
        containerId, ksnkoId, limit, offset, from, to,
    )

    /**
     * TODO doc
     */
    suspend fun getSortedWasteStationsPickDays(
        sensoneoCode: String?,
        ksnkoId: String?
    ) = repository.getSortedWasteStationsPickDays(sensoneoCode, ksnkoId)
}
