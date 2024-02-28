package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.network.IGolemioApi
import cz.vse.golemiokotlinlib.v2.service.ISortedWasteStationsRepository

internal class SortedWasteStationsRemoteRepository(
    private val api: IGolemioApi
) : ISortedWasteStationsRepository {
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
    ) = api.getSortedWasteStations(
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

    override suspend fun getSortedWasteStationsMeasurements(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ) = api.getSortedWasteStationsMeasurements(
        containerId, ksnkoId, limit, offset, from, to,
    )

    override suspend fun getSortedWasteStationsPicks(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ) = api.getSortedWasteStationsPicks(
        containerId, ksnkoId, limit, offset, from, to,
    )

    override suspend fun getSortedWasteStationsPickDays(
        sensoneoCode: String?,
        ksnkoId: String?
    ) = api.getSortedWasteStationsPickDays(sensoneoCode, ksnkoId)
}
