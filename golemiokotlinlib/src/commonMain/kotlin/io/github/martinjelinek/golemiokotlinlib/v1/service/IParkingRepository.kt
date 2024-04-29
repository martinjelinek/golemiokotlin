package io.github.martinjelinek.golemiokotlinlib.v1.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.ParkingV1
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.ParkingsV1History

interface IParkingRepository {

    suspend fun getAllParkingsV1(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<ParkingV1>

    suspend fun getParkingsV1ById(id: String): ParkingV1

    suspend fun getParkingsV1History(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String,
    ) : List<ParkingsV1History>
}
