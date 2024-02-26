package cz.vse.golemiokotlinlib.api.model.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicalInstitutionTypes(
    val pharmacies: List<String>? = null,
    @SerialName("health_care")
    val healthCare: List<String>? = null,
) : ResponseData
