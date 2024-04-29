package io.github.martinjelinek.golemiokotlinlib.v1.service.impl.cache

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.ParkingV1
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.ParkingsV1History
import io.github.martinjelinek.golemiokotlinlib.common.network.GolemioApi
import io.github.martinjelinek.golemiokotlinlib.common.service.impl.cache.CachingRepository
import io.github.martinjelinek.golemiokotlinlib.v1.service.IParkingRepository
import io.github.martinjelinek.golemiokotlinlib.v1.service.impl.remote.ParkingRemoteRepository

internal class ParkingCachingRepository(
    private val remoteRepository: IParkingRepository
) : IParkingRepository, CachingRepository() {

    private var parkings: MutableMap<String, List<ParkingV1>> =
        mutableMapOf()

    private var parkingsById: MutableMap<String, ParkingV1> =
        mutableMapOf()

    private var parkingsHistory: MutableMap<String, List<ParkingsV1History>> =
        mutableMapOf()

    override suspend fun getAllParkingsV1(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<ParkingV1> {
        return fetchDataAndCache(
            parkings,
            latlng,
            range,
            districts,
            limit,
            offset,
            updatedSince
        ) {
            remoteRepository.getAllParkingsV1(latlng, range, districts, limit, offset, updatedSince)
        }
    }

    override suspend fun getParkingsV1ById(id: String): ParkingV1 {
        return fetchDataAndCache(
            parkingsById,
            id
        ) {
            remoteRepository.getParkingsV1ById(id)
        }
    }

    override suspend fun getParkingsV1History(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String
    ): List<ParkingsV1History> {
        return fetchDataAndCache(
            parkingsHistory,
            limit,
            offset,
            from,
            to,
            sensorId
        ) {
            remoteRepository.getParkingsV1History(limit, offset, from, to, sensorId)
        }
    }

    companion object Factory {

        /**
         * Creates [ParkingCachingRepository] over [ParkingRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            ParkingCachingRepository(ParkingRemoteRepository(GolemioApi(apiKey)))
    }
}