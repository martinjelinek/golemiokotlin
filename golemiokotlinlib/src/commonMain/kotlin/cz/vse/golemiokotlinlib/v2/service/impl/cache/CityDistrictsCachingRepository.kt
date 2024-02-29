package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.common.entity.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.common.network.GolemioApi
import cz.vse.golemiokotlinlib.common.service.impl.cache.CachingRepository
import cz.vse.golemiokotlinlib.v2.service.ICityDistrictsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.BicycleCountersRemoteRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.CityDistrictsRemoteRepository

/**
 * Repository for caching city district data requests.
 * Non-persistent cache using kotlin collections.
 */
internal class CityDistrictsCachingRepository(
    private val remoteRepository: ICityDistrictsRepository
) :
    ICityDistrictsRepository, CachingRepository() {

    private var cityDistricts: MutableMap<String, List<CityDistrict>> =
        mutableMapOf()

    private var cityDistrictsById: MutableMap<String, CityDistrict> =
        mutableMapOf()

    override suspend fun getAllCityDistricts(
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<CityDistrict> {
        return fetchDataAndCache(cityDistricts, latlng, range, limit, offset, updatedSince) {
            remoteRepository.getAllCityDistricts(latlng, range, limit, offset, updatedSince)
        }
    }

    override suspend fun getCityDistrictById(id: String): CityDistrict {
        return fetchDataAndCache(cityDistrictsById, id) {
            remoteRepository.getCityDistrictById(id)
        }
    }

    companion object Factory {

        /**
         * Creates [BicycleCountersCachingRepository] over [BicycleCountersRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            CityDistrictsCachingRepository(CityDistrictsRemoteRepository(GolemioApi(apiKey)))
    }
}
