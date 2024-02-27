package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.IBicycleCountersRepository

/**
 * Repository handling bicycle counters requests via [api].
 */
internal class BicycleCountersRemoteRepository(
    private val api: GolemioApi
) : IBicycleCountersRepository {
    override suspend fun getAllBicycleCounters(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?
    ) = api.getAllBicycleCounters(latlng, range, limit, offset)

    override suspend fun getBicycleCountersDetections(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String
    ) = api.getBicycleCountersDetections(limit, offset, from, to, aggregate, id)

    override suspend fun getBicycleCountersTemperatures(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?
    ) = api.getBicycleCountersTemperatures(limit, offset, from, to, aggregate, ids)
}
