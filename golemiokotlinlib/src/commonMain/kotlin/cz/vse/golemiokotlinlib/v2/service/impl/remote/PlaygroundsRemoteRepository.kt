package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.common.network.IGolemioApi
import cz.vse.golemiokotlinlib.v2.service.IPlaygroundsRepository

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
