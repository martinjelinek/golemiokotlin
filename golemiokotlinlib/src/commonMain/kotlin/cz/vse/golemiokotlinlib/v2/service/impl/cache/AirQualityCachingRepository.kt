package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.CachingRepository
import cz.vse.golemiokotlinlib.v2.service.IAirQualityRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.AirQualityRemoteRepository

/**
 * Repository for caching air quality data requests.
 * Non-persistent cache using kotlin collections.
 */
internal class AirQualityCachingRepository(
    private val remoteRepository: AirQualityRemoteRepository,
) : IAirQualityRepository, CachingRepository() {

    private val airQualityStationsCache: MutableMap<String, List<AirQualityStation>> =
        mutableMapOf()
    private var airQualityStationsHistoryCache: MutableMap<String, List<AirQualityStationHistory>> =
        mutableMapOf()

    override suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation> {
        return fetchDataAndCache(
            airQualityStationsCache,
            latlng,
            range,
            districts,
            limit,
            offset,
            updatedSince
        ) {
            remoteRepository.getAllAirQualityStations(
                latlng,
                range,
                districts,
                limit,
                offset,
                updatedSince
            )
        }
    }

    override suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory> {
        return fetchDataAndCache(
            airQualityStationsHistoryCache,
            sensorId,
            limit,
            offset,
            from,
            to
        ) {
            remoteRepository.getAirQualityStationsHistory(
                sensorId,
                limit,
                offset,
                from,
                to
            )
        }
    }

    companion object Factory {
        /**
         * Creates [AirQualityCachingRepository] over [AirQualityRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            AirQualityCachingRepository(AirQualityRemoteRepository(GolemioApi(apiKey)))
    }
}
