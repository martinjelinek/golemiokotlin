package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPicks
import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.CachingRepository
import cz.vse.golemiokotlinlib.v2.service.ISortedWasteStationsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.SortedWasteStationsRemoteRepository

internal class SortedWasteStationsCachingRepository(
    private val remoteRepository: ISortedWasteStationsRepository
) : ISortedWasteStationsRepository, CachingRepository() {

    private var sortedWasteStations: MutableMap<String, List<WasteCollection>> =
        mutableMapOf()
    private var sortedWasteStationsMeasurements: MutableMap<String, List<WasteStationMeasurements>> =
        mutableMapOf()
    private var sortedWasteStationsPicks: MutableMap<String, List<WasteStationsPicks>> =
        mutableMapOf()
    private var sortedWasteStationsPickDays: MutableMap<String, List<WasteStationsPickDays>> =
        mutableMapOf()

    override suspend fun getSortedWasteStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        accessibility: WasteStationAccessibility?,
        limit: Int?,
        offset: Int?,
        onlyMonitored: Boolean?,
        id: String,
        ksnkoId: String
    ): List<WasteCollection> {
        return fetchDataAndCache(
            sortedWasteStations,
            latlng,
            range,
            districts,
            accessibility,
            limit,
            offset,
            onlyMonitored,
            id,
            ksnkoId
        ) {
            remoteRepository.getSortedWasteStations(
                latlng,
                range,
                districts,
                accessibility,
                limit,
                offset,
                onlyMonitored,
                id,
                ksnkoId
            )
        }
    }

    override suspend fun getSortedWasteStationsMeasurements(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<WasteStationMeasurements> {
        return fetchDataAndCache(
            sortedWasteStationsMeasurements,
            containerId,
            ksnkoId,
            limit,
            offset,
            from,
            to,
        ) {
            remoteRepository.getSortedWasteStationsMeasurements(
                containerId,
                ksnkoId,
                limit,
                offset,
                from,
                to
            )
        }
    }

    override suspend fun getSortedWasteStationsPicks(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<WasteStationsPicks> {
        return fetchDataAndCache(
            sortedWasteStationsPicks,
            containerId,
            ksnkoId,
            limit,
            offset,
            from,
            to,
        ) {
            remoteRepository.getSortedWasteStationsPicks(
                containerId,
                ksnkoId,
                limit,
                offset,
                from,
                to
            )
        }
    }

    override suspend fun getSortedWasteStationsPickDays(
        sensoneoCode: String?,
        ksnkoId: String?
    ): List<WasteStationsPickDays> {
        return fetchDataAndCache(
            sortedWasteStationsPickDays,
            sensoneoCode,
            ksnkoId,
        ) {
            remoteRepository.getSortedWasteStationsPickDays(sensoneoCode, ksnkoId)
        }
    }

    companion object Factory {

        /**
         * Creates [SortedWasteStationsCachingRepository] over [SortedWasteStationsRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            SortedWasteStationsCachingRepository(
                SortedWasteStationsRemoteRepository(
                    GolemioApi(
                        apiKey
                    )
                )
            )
    }
}