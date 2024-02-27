package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory

/**
 * Interface for remote repository handling air quality data requests.
 */
internal interface IAirQualityRepository {

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
