package io.github.martinjelinek.golemiokotlinlib.v2.service.impl.remote

import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MedicalGroup
import io.github.martinjelinek.golemiokotlinlib.common.network.IGolemioApi
import io.github.martinjelinek.golemiokotlinlib.v2.service.IMedicalInstitutionsRepository

/**
 * Repository handling medical institutions requests via [api].
 */
internal class MedicalInstitutionsRemoteRepository(
    private val api: IGolemioApi
) : IMedicalInstitutionsRepository {
    override suspend fun getAllMedicalInstitutions(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllMedicalInstitutions(latlng, range, districts, group, limit, offset, updatedSince)

    override suspend fun getMedicalInstitutionById(id: String) = api.getMedicalInstitutionById(id)

    override suspend fun getMedicalInstitutionTypes() = api.getMedicalInstitutionTypes()
}
