package io.github.martinjelinek.golemiokotlinlib.v2.client

import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.WasteStationAccessibility
import io.github.martinjelinek.golemiokotlinlib.v2.service.IWasteCollectionRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache.WasteCollectionCachingRepository

/**
 * Exposed client class providing bicycle counters data requests.
 */
class WasteCollectionClient(apiKey: String) {

    private val repository: IWasteCollectionRepository = WasteCollectionCachingRepository.create(apiKey)

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
     * TODO 29/2/24 this method returns 403: Forbidden even on golemio try it out page.
     */
//    suspend fun getSortedWasteStationsMeasurements(
//        containerId: String,
//        ksnkoId: String?,
//        limit: Int?,
//        offset: Int?,
//        from: String?,
//        to: String?
//    ) = repository.getSortedWasteStationsMeasurements(
//        containerId, ksnkoId, limit, offset, from, to,
//    )

    /**
     * TODO 29/2/24 this method returns 403: Forbidden even on golemio try it out page.
     */
//    suspend fun getSortedWasteStationsPicks(
//        containerId: String,
//        ksnkoId: String?,
//        limit: Int?,
//        offset: Int?,
//        from: String?,
//        to: String?
//    ) = repository.getSortedWasteStationsPicks(
//        containerId, ksnkoId, limit, offset, from, to,
//    )

    /**
     * TODO doc
     */
    suspend fun getSortedWasteStationsPickDays(
        sensoneoCode: String?,
        ksnkoId: String?
    ) = repository.getSortedWasteStationsPickDays(sensoneoCode, ksnkoId)
}
