package io.github.martinjelinek.golemiokotlinlib.v2.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.CityDistrict

/**
 * Interface for repositories handling city districts data requests.
 */
interface ICityDistrictsRepository {

    /**
     * @return A list of [CityDistrict]s.
     */
    suspend fun getAllCityDistricts(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<CityDistrict>

    /**
     * Gets [CityDistrict] by it's [id].
     */
    suspend fun getCityDistrictById(
        id: String
    ): CityDistrict
}
