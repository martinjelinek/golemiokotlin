package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IAirQualityRepository

/**
 * Repository handling air quality data requests via [api].
 */
internal class AirQualityRemoteRepository(
    private val api: IGolemioApi,
) : IAirQualityRepository {
    override suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllAirQualityStations(
        latlng,
        range,
        districts,
        limit,
        offset,
        updatedSince,
    )

    override suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ) = api.getAirQualityStationsHistory(sensorId, limit, offset, from, to)
}
