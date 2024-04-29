package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.ICityDistrictsRepository

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
