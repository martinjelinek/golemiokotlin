package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.v2.network.IGolemioApi
import cz.vse.golemiokotlinlib.v2.service.ICityDistrictsRepository

/**
 * Repository handling city districts requests via [api].
 */
internal class CityDistrictsRemoteRepository(
    private val api: IGolemioApi,
) : ICityDistrictsRepository {
    override suspend fun getAllCityDistricts(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllCityDistricts(latlng, range, limit, offset, updatedSince)

    override suspend fun getCityDistrictById(id: String) = api.getCityDistrictById(id)
}
