package io.github.martinjelinek.golemiokotlinlib.v2.client

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.AirQualityStation
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.AirQualityStationHistory
import io.github.martinjelinek.golemiokotlinlib.v2.service.IAirQualityRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache.AirQualityCachingRepository

/**
 * Exposed client class providing air quality data requests.
 */
class AirQualityClient(
    apiKey: String,
) {

    private val repository: IAirQualityRepository = AirQualityCachingRepository.create(apiKey)

    /**
     * @param latlng pair of coordinates
     * @param range range in m
     * @param districts list of prague city districts
     * @param limit limit
     * @param offset offset
     * @param updatedSince updated since
     *
     * @return A list of [AirQualityStation]s.
     */
    suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ) = repository.getAllAirQualityStations(
        latlng, range, districts, limit, offset, updatedSince
    )

    /**
     * @param sensorId id of sensor of requested station
     * @param limit limit
     * @param offset offset
     * @param from timestamp when to get the data from
     * @param to timestamp when to get the data to
     *
     * @return [AirQualityStationHistory].
     */
    suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ) = repository.getAirQualityStationsHistory(
        sensorId, limit, offset, from, to
    )
}
