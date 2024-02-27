package cz.vse.golemiokotlinlib.v2

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.v2.service.IAirQualityRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.AirQualityCachingRepository

/**
 * Exposed client class providing air quality data requests.
 */
class AirQualityClient(
    apiKey: String,
) {

    private val repository: IAirQualityRepository = AirQualityCachingRepository.create(apiKey)

    /**
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
