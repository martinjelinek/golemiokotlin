package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.common.entity.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.v2.service.ICityDistrictsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.CityDistrictsCachingRepository

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
