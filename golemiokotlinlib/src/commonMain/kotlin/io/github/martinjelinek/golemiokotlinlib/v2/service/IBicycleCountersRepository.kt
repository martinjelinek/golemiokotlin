package io.github.martinjelinek.golemiokotlinlib.v2.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.BicycleCounter
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.BicycleCounterDetection
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.BicycleCounterTemperature

/**
 * Interface for repositories handling bicycle counters data requests.
 */
interface IBicycleCountersRepository {

    /**
     * @return A list of [BicycleCounter]s.
     */
    suspend fun getAllBicycleCounters(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
    ): List<BicycleCounter>

    /**
     * @return list of [BicycleCounterDetection]s.
     */
    suspend fun getBicycleCountersDetections(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String,
    ) : List<BicycleCounterDetection>

    /**
     * @return [BicycleCounterTemperature]s.
     */
    suspend fun getBicycleCountersTemperatures(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?,
    ) : List<BicycleCounterTemperature>
}
