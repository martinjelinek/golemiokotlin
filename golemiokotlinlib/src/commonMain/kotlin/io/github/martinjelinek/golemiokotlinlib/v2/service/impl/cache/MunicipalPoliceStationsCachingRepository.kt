package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MunicipalPoliceStation
import io.github.martinjelinek.golemiokotlinlib.common.network.GolemioApi
import io.github.martinjelinek.golemiokotlinlib.common.service.impl.cache.CachingRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalPoliceStationsRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote.MunicipalPoliceStationsRemoteRepository

internal class MunicipalPoliceStationsCachingRepository(
    private val remoteRepository: IMunicipalPoliceStationsRepository
) : IMunicipalPoliceStationsRepository, CachingRepository() {

    private var policeStations: MutableMap<String, List<MunicipalPoliceStation>> =
        mutableMapOf()
    private var policeStationsById: MutableMap<String, MunicipalPoliceStation> =
        mutableMapOf()

    override suspend fun getAllMunicipalPoliceStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalPoliceStation> {
        return fetchDataAndCache(
            policeStations,
            latlng,
            range,
            districts,
            limit,
            offset,
            updatedSince
        ) {
            remoteRepository.getAllMunicipalPoliceStations(
                latlng,
                range,
                districts,
                limit,
                offset,
                updatedSince
            )
        }
    }

    override suspend fun getMunicipalPoliceStationById(id: String): MunicipalPoliceStation {
        return fetchDataAndCache(policeStationsById, id) {
            remoteRepository.getMunicipalPoliceStationById(id)
        }
    }

    companion object Factory {

        /**
         * Creates [MunicipalPoliceStationsCachingRepository] over [MunicipalPoliceStationsRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            MunicipalPoliceStationsCachingRepository(
                MunicipalPoliceStationsRemoteRepository(
                    GolemioApi(apiKey)
                )
            )
    }
}
