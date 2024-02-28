package cz.vse.jelinekma.pragueopendatakotlinlib.dummyData

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStationsProperties
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AveragedTime
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Component
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Measurement
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.v2.service.IAirQualityRepository

class DummyAirQualityRepository : IAirQualityRepository {
    override suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<AirQualityStation> {
        return listOf(
            AirQualityStation(
                AirQualityStationsProperties(
                    "AKALA",
                    "Praha 8-Karlín",
                    "praha-8",
                    Measurement(
                        "1A",
                        listOf(
                            Component(AveragedTime(3, 16.8), "NO2"),
                            Component(AveragedTime(3, 14.6), "PM10")
                        )
                    ),
                    "2024-02-28T12:15:05.044Z"
                )
            ),
            AirQualityStation(
                AirQualityStationsProperties(
                    "AVYNA",
                    "Praha 9-Vysočany",
                    "praha-9",
                    Measurement(
                        "1B",
                        listOf(
                            Component(AveragedTime(3, 25.5), "NO2"),
                            Component(AveragedTime(3, 19.6), "O3"),
                            Component(AveragedTime(3, 35.3), "PM10")
                        )
                    ),
                    "2024-02-28T12:15:05.045Z"
                )
            )
        )
    }

    override suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<AirQualityStationHistory> {
        return listOf(
            AirQualityStationHistory(
                "ACHOA",
                Measurement(
                    "2B",
                    listOf(
                        Component(AveragedTime(3, 12.8), "NO2"),
                        Component(AveragedTime(3, 23.4), "PM10")
                    )
                )
            ),
            AirQualityStationHistory(
                "ACHOA",
                Measurement(
                    "2B",
                    listOf(
                        Component(AveragedTime(3, 9.8), "NO2"),
                        Component(AveragedTime(3, 18.2), "PM10")
                    )
                )
            )
        )
    }
}
