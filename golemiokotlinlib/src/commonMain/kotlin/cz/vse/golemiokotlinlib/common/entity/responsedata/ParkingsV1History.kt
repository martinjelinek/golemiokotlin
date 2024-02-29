package cz.vse.golemiokotlinlib.common.entity.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParkingsV1History(
    val id: String,
    @SerialName("num_of_free_places")
    val numOfFreePlaces: Int,
    @SerialName("num_of_taken_places")
    val numOfTakenPlaces: Int,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("total_num_of_places")
    val totalNumOfPlaces: Int,
    @SerialName("last_updated")
    val lastUpdated: Long? = null
): ResponseData
