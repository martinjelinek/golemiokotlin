package cz.vse.golemiokotlinlib.common.entity.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MedicalInstitutionTypes(
    val pharmacies: List<String>? = null,
    @SerialName("health_care")
    val healthCare: List<String>? = null,
) : ResponseData

enum class MedicalGroup(val group: String) {
    PHARMACIES("pharmacies"),
    HEALTH_CARE("health_care"),
}