package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.Garden
import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IGardensRepository

/**
 * Repository handling garden requests via [api].
 */
class GardensRemoteRepository(
    private val api: IGolemioApi
) : IGardensRepository {
    /**
     * @return list of [Garden]s.
     */
    override suspend fun getAllGardens(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllGardens(latlng, range, districts, limit, offset, updatedSince)

    /**
     * @return [Garden] with [id].
     */
    override suspend fun getGardenById(id: String) = api.getGardenById(id)
}
