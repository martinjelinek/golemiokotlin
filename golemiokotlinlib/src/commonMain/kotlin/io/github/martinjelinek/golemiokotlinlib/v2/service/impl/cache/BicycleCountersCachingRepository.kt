package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.BicycleCounter
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.BicycleCounterDetection
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.BicycleCounterTemperature
import io.github.martinjelinek.golemiokotlinlib.common.network.GolemioApi
import io.github.martinjelinek.golemiokotlinlib.common.service.impl.cache.CachingRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.IBicycleCountersRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote.BicycleCountersRemoteRepository

/**
 * Repository for caching bicycle counters data requests.
 * Non-persistent cache using kotlin collections.
 */
internal class BicycleCountersCachingRepository(
    private val remoteRepository: IBicycleCountersRepository,
) : IBicycleCountersRepository, CachingRepository() {

    private var bicycleCounters: MutableMap<String, List<BicycleCounter>> =
        mutableMapOf()
    private var bicycleCounterDetections: MutableMap<String, List<BicycleCounterDetection>> =
        mutableMapOf()
    private var bicycleCounterTemperatures: MutableMap<String, List<BicycleCounterTemperature>> =
        mutableMapOf()

    override suspend fun getAllBicycleCounters(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?
    ): List<BicycleCounter> {
        return fetchDataAndCache(
            bicycleCounters,
            latlng,
            range,
            offset
        ) {
            remoteRepository.getAllBicycleCounters(
                latlng,
                range,
                limit,
                offset
            )
        }
    }

    override suspend fun getBicycleCountersDetections(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String
    ): List<BicycleCounterDetection> {
        return fetchDataAndCache(
            bicycleCounterDetections,
            limit,
            offset,
            from,
            to,
            aggregate,
            id,
        ) {
            remoteRepository.getBicycleCountersDetections(
                limit,
                offset,
                from,
                to,
                aggregate,
                id,
            )
        }
    }

    override suspend fun getBicycleCountersTemperatures(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?
    ): List<BicycleCounterTemperature> {
        return fetchDataAndCache(
            bicycleCounterTemperatures,
            limit,
            offset,
            from,
            to,
            aggregate,
            ids,
        ) {
            remoteRepository.getBicycleCountersTemperatures(
                limit,
                offset,
                from,
                to,
                aggregate,
                ids,
            )
        }
    }

    companion object Factory {

        /**
         * Creates [BicycleCountersCachingRepository] over [BicycleCountersRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            BicycleCountersCachingRepository(BicycleCountersRemoteRepository(GolemioApi(apiKey)))
    }
}
