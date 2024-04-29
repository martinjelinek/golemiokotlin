package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IMunicipalLibrariesRepository

internal class MunicipalLibrariesRemoteRepository(private val api: IGolemioApi) : IMunicipalLibrariesRepository {
    override suspend fun getAllMunicipalLibraries(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllMunicipalLibraries(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getMunicipalLibraryById(id: String) = api.getMunicipalLibraryById(id)
}
