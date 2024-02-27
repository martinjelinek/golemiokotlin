package cz.vse.golemiokotlinlib.v2.entity.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicalInstitutionTypes(
    val pharmacies: List<String>? = null,
    @SerialName("health_care")
    val healthCare: List<String>? = null,
) : ResponseData
