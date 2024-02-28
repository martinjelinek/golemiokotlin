package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Parking
import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.CachingRepository
import cz.vse.golemiokotlinlib.v2.service.IParkingRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.ParkingRemoteRepository

internal class ParkingCachingRepository(
    private val remoteRepository: IParkingRepository
) : IParkingRepository, CachingRepository() {

    private var parkings: MutableMap<String, List<Parking>> =
        mutableMapOf()

    override suspend fun getAllParkings(
        latlng: Pair<String, String>?,
        range: Int?,
        source: String?,
        sourceId: String?,
        category: List<String>,
        limit: Int?,
        offset: Int?,
        minutesBefore: Int?,
        updatedSince: String?
    ): List<Parking> {
        return fetchDataAndCache(
            parkings,
            latlng,
            range,
            source,
            sourceId,
            category,
            limit,
            offset,
            minutesBefore,
            updatedSince
        ) {
            remoteRepository.getAllParkings(
                latlng,
                range,
                source,
                sourceId,
                category,
                limit,
                offset,
                minutesBefore,
                updatedSince
            )
        }
    }

    override suspend fun getParkingById(id: String): Parking {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingDetail() {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingDetailById(id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingMeasurements() {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingTariffs() {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingTariffsByTariffId() {
        TODO("Not yet implemented")
    }

    companion object Factory {

        /**
         * Creates [ParkingCachingRepository] over [ParkingRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            ParkingCachingRepository(ParkingRemoteRepository(GolemioApi(apiKey)))
    }
}