package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.v2.entity.responsedata.BicycleCounterDetection
import cz.vse.golemiokotlinlib.v2.entity.responsedata.BicycleCounterTemperature

/**
 * Interface for remote repository handling bicycle counters data requests.
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
