package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.IAirQualityRemoteRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.AirQualityRemoteRepository

/**
 * Repository for caching air quality data requests.
 * Non-persistent cache using kotlin collections.
 */
internal class AirQualityCachingRepository(
    private val remoteRepository: AirQualityRemoteRepository,
) : IAirQualityRemoteRepository {

    private var airQualityStations: List<AirQualityStation> = emptyList()
    private var airQualityStationsHistory: List<AirQualityStationHistory> = emptyList()

    override suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation> = airQualityStations.ifEmpty {
        remoteRepository.getAllAirQualityStations(
            latlng,
            range,
            districts,
            limit,
            offset,
            updatedSince
        ).also { airQualityStations = it }
    }

    override suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory> = airQualityStationsHistory.ifEmpty {
        remoteRepository.getAirQualityStationsHistory(
            sensorId, limit, offset, from, to
        ).also { airQualityStationsHistory = it }
    }

    companion object Factory {

        /**
         * Creates [AirQualityCachingRepository] over [AirQualityRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            AirQualityCachingRepository(AirQualityRemoteRepository(GolemioApi(apiKey)))
    }
}
