package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.common.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.common.entity.responsedata.AirQualityStationHistory

/**
 * Interface for remote repository handling air quality data requests.
 */
interface IAirQualityRepository {

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
    ): List<AirQualityStation>

    /**
     * @return [AirQualityStationHistory].
     */
    suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory>
}
