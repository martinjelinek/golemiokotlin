package cz.vse.golemiokotlinlib.v2.entity.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServedActivity(
    val activity: String,
    @SerialName("number_of_person_in_queue")
    val numberOfPersonInQueue: Int,
    @SerialName("number_of_serving_counters")
    val numberOfServingCounters: Int
)

@Serializable
data class MunicipalAuthorityQueue(
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("municipal_authority_id")
    val municipalAuthorityId: String,
    @SerialName("served_activities")
    val servedActivities: List<ServedActivity>,
    @SerialName("updated_at")
    val updatedAt: String,
    val title: String
) : ResponseData

enum class MunicipalAuthorityType(val type: String) {
    MUNICIPALITY("municipality"),
    CITY_HALL("city_hall"),
}
