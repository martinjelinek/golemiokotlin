package cz.vse.golemiokotlinlib.v2.client

import cz.vse.golemiokotlinlib.common.entity.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalGroup
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalInstitutionTypes
import cz.vse.golemiokotlinlib.v2.service.IMedicalInstitutionsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.cache.MedicalInstitutionsCachingRepository

/**
 * Exposed client class providing medical institutions data requests.
 */
class MedicalInstitutionsClient(apiKey: String) {

    private val repository: IMedicalInstitutionsRepository = MedicalInstitutionsCachingRepository.create(apiKey)

    /**
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return A list of [MedicalInstitution]s.
     */
    suspend fun getAllMedicalInstitutions(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = repository.getAllMedicalInstitutions(latlng, range, districts, group, limit, offset, updatedSince)


    /**
     * @param id Identifier of the medical institution; example: 252671-fakultni-nemocnice-v-motole
     *
     * @return [MedicalInstitution]
     */
    suspend fun getMedicalInstitutionById(id: String) = repository.getMedicalInstitutionById(id)

    /**
     * @return [MedicalInstitutionTypes]
     */
    suspend fun getMedicalInstitutionTypes() = repository.getMedicalInstitutionTypes()
}
