package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IPlaygroundsRepository

internal class PlaygroundsRemoteRepository(private val api: IGolemioApi) : IPlaygroundsRepository {
    override suspend fun getAllPlaygrounds(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllPlaygrounds(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getPlaygroundById(id: String) = api.getPlaygroundById(id)

    override suspend fun getPlaygroundsProperties() = api.getPlaygroundsProperties()
}
