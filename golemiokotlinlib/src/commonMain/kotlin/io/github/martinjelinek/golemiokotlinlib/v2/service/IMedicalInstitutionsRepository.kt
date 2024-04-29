package io.github.martinjelinek.golemiokotlinlib.v2.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MedicalInstitution
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MedicalGroup
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MedicalInstitutionTypes

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
