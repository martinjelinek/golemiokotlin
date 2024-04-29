package io.github.martinjelinek.golemiokotlinlib.v1.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v1.service.IParkingRepository

internal class ParkingRemoteRepository(private val api: IGolemioApi) : IParkingRepository {
    override suspend fun getAllParkingsV1(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllParkingsV1(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getParkingsV1ById(id: String) = api.getParkingsV1ById(id)

    override suspend fun getParkingsV1History(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String
    ) = api.getParkingsV1History(limit, offset, from, to, sensorId)
}
