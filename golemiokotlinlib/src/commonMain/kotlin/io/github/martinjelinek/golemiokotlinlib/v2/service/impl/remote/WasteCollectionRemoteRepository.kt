package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.WasteStationAccessibility
import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IWasteCollectionRepository

internal class WasteCollectionRemoteRepository(
    private val api: IGolemioApi
) : IWasteCollectionRepository {
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
