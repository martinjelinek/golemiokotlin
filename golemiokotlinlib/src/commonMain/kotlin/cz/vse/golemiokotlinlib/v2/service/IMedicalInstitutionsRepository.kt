package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalGroup
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalInstitutionTypes

interface IMedicalInstitutionsRepository {

    suspend fun getAllMedicalInstitutions(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MedicalInstitution>

    suspend fun getMedicalInstitutionById(id: String): MedicalInstitution

    suspend fun getMedicalInstitutionTypes(): MedicalInstitutionTypes
}
