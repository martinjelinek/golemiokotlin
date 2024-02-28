package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MunicipalAuthority
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MunicipalAuthorityQueue
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MunicipalAuthorityType
import cz.vse.golemiokotlinlib.v2.service.IMunicipalAuthoritiesRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.MunicipalAuthoritiesCachingRepository

/**
 * Exposed client class providing municipal authorities data requests.
 */
class MunicipalAuthoritiesClient(apiKey: String) {

    private val repository: IMunicipalAuthoritiesRepository = MunicipalAuthoritiesCachingRepository.create(apiKey)

    /**
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param type one of [MunicipalAuthorityType]
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return A list of [MunicipalAuthority].
     */
    suspend fun getAllMunicipalAuthorities(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ) = repository.getAllMunicipalAuthorities(latlng, range, districts, type, limit, offset, updatedSince)

    /**
     * @return [MunicipalAuthority] by it's [id].
     */
    suspend fun getMunicipalAuthorityById(id: String) = repository.getMunicipalAuthorityById(id)

    /**
     * @param id Identifier of the municipality. Example: skoduv-palac
     *
     * @return [MunicipalAuthorityQueue].
     */
    suspend fun getMunicipalAuthoritiesQueues(id: String) = repository.getMunicipalAuthorityById(id)
}
