package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalGroup
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalInstitutionTypes
import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.CachingRepository
import cz.vse.golemiokotlinlib.v2.service.IMedicalInstitutionsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.MedicalInstitutionsRemoteRepository

/**
 * Repository for caching medical institutions data requests.
 * Non-persistent cache using kotlin collections.
 */
internal class MedicalInstitutionsCachingRepository(
    private val remoteRepository: IMedicalInstitutionsRepository
) : IMedicalInstitutionsRepository, CachingRepository() {

    private var medicalInstitutions: MutableMap<String, List<MedicalInstitution>> =
        mutableMapOf()
    private var medicalInstitutionsById: MutableMap<String, MedicalInstitution> =
        mutableMapOf()
    private var medicalInstitutionTypes: MedicalInstitutionTypes = MedicalInstitutionTypes()

    override suspend fun getAllMedicalInstitutions(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MedicalInstitution> {
        return fetchDataAndCache(
            medicalInstitutions,
            latlng,
            range,
            districts,
            group,
            limit,
            offset,
            updatedSince,
        ) {
            remoteRepository.getAllMedicalInstitutions(
                latlng,
                range,
                districts,
                group,
                limit,
                offset,
                updatedSince
            )
        }
    }

    override suspend fun getMedicalInstitutionById(id: String): MedicalInstitution {
        return fetchDataAndCache(medicalInstitutionsById, id) {
            remoteRepository.getMedicalInstitutionById(id)
        }
    }

    override suspend fun getMedicalInstitutionTypes(): MedicalInstitutionTypes {
        return if (medicalInstitutionTypes.healthCare == null && medicalInstitutionTypes.pharmacies == null) {
            remoteRepository.getMedicalInstitutionTypes().also {
                medicalInstitutionTypes = it
            }
        } else {
            medicalInstitutionTypes
        }
    }

    companion object Factory {

        /**
         * Creates [MedicalInstitutionsCachingRepository] over [MedicalInstitutionsRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            MedicalInstitutionsCachingRepository(
                MedicalInstitutionsRemoteRepository(
                    GolemioApi(
                        apiKey
                    )
                )
            )
    }
}
