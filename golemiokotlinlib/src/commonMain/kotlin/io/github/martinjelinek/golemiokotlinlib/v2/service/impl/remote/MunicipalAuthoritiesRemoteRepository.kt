package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityType
import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalAuthoritiesRepository

/**
 * Remote repo for municipal authorities data requests using [api].
 */
internal class MunicipalAuthoritiesRemoteRepository(
    private val api: IGolemioApi
) : IMunicipalAuthoritiesRepository {
    override suspend fun getAllMunicipalAuthorities(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllMunicipalAuthorities(latlng, range, districts, type, limit, offset, updatedSince)

    override suspend fun getMunicipalAuthorityById(id: String) = api.getMunicipalAuthorityById(id)

    override suspend fun getMunicipalAuthoritiesQueues(id: String) = api.getMunicipalAuthoritiesQueues(id)
}
