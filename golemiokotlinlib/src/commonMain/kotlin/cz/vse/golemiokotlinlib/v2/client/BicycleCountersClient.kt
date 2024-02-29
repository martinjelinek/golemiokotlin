package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.common.entity.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.common.entity.responsedata.BicycleCounterDetection
import cz.vse.golemiokotlinlib.common.entity.responsedata.BicycleCounterTemperature
import cz.vse.golemiokotlinlib.v2.service.IBicycleCountersRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.BicycleCountersCachingRepository

/**
 * Exposed client class providing bicycle counters data requests.
 */
class BicycleCountersClient(apiKey: String) {

    private val repository: IBicycleCountersRepository = BicycleCountersCachingRepository.create(apiKey)

    /**
     * @return list of [BicycleCounter]s.
     */
    suspend fun getAllBicycleCounters(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?
    ) = repository.getAllBicycleCounters(
        latlng, range, limit, offset,
    )

    /**
     * @return list of [BicycleCounterDetection]s.
     */
    suspend fun getBicycleCountersDetections(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String
    ) = repository.getBicycleCountersDetections(
        limit, offset, from, to, aggregate, id,
    )

    /**
     * @return list of [BicycleCounterTemperature]s.
     */
    suspend fun getBicycleCountersTemperatures(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?
    ) = repository.getBicycleCountersTemperatures(
        limit, offset, from, to, aggregate, ids,
    )
}
