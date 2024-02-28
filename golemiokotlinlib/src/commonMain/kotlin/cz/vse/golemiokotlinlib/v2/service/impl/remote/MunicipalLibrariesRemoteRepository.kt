package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.v2.network.IGolemioApi
import cz.vse.golemiokotlinlib.v2.service.IMunicipalLibrariesRepository

class MunicipalLibrariesRemoteRepository(private val api: IGolemioApi) : IMunicipalLibrariesRepository {
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
