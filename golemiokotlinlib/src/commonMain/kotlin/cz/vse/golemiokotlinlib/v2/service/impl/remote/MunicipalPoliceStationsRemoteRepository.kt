package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.v2.network.IGolemioApi
import cz.vse.golemiokotlinlib.v2.service.IMunicipalPoliceStationsRepository

internal class MunicipalPoliceStationsRemoteRepository(
    private val api: IGolemioApi
) : IMunicipalPoliceStationsRepository {
    override suspend fun getAllMunicipalPoliceStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllMunicipalPoliceStations(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getMunicipalPoliceStationById(id: String) = api.getMunicipalPoliceStationById(id)
}
