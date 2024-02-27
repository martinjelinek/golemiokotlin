package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory

/**
 * Interface for remote repository handling air quality data requests.
 */
internal interface IAirQualityRemoteRepository {

    /**
     * @return A list of [AirQualityStation]s.
     */
    suspend fun getAllAirQualityStations(
        // todo zminit odchyleni latlng
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation>

    /**
     * @return [AirQualityStationHistory].
     * TODO v textu poreferovat ze Golemio v "try it out" nic nevraci
     * TODO edit - vraci, ale musi se zmenit rok na pozdejsi - kam az?
     */
    suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory>
}
