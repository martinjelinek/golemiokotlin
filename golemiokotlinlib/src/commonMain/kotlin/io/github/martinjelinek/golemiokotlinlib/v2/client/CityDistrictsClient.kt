package io.github.martinjelinek.golemiokotlinlib.v2.client

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.CityDistrict
import io.github.martinjelinek.golemiokotlinlib.v2.service.ICityDistrictsRepository
import io.github.martinjelinek.golemiokotlinlib.v2.service.impl.cache.CityDistrictsCachingRepository

/**
 * Exposed client class providing city district data.
 */
class CityDistrictsClient(
    apiKey: String,
) {

    private val repository: ICityDistrictsRepository = CityDistrictsCachingRepository.create(apiKey)

    /**
     * @return list of [CityDistrict]s.
     */
    suspend fun getAllCityDistricts(
        latlng: Pair<String, String>?, range: Int?, limit: Int?, offset: Int?, updatedSince: String?
    ) = repository.getAllCityDistricts(latlng, range, limit, offset, updatedSince)

    /**
     * @return [CityDistrict] with [id].
     */
    suspend fun getCityDistrictById(id: String) = repository.getCityDistrictById(id)
}
