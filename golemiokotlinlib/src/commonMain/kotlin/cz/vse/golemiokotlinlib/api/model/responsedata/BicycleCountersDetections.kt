package cz.vse.golemiokotlinlib.api.model.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BicycleCounterDetection(
    val id: String,
    val value: Int,
    @SerialName("value_pedestrians")
    val valuePedestrians: Int,
    @SerialName("locations_id")
    val locationsId: String,
    @SerialName("measured_from")
    val measuredFrom: String,
    @SerialName("measured_to")
    val measuredTo: String,
    @SerialName("measurement_count")
    val measurementCount: Int,
) : ResponseData

@Serializable
data class BicycleCounterTemperature(
    val id: String,
    val value: Int,
    @SerialName("locations_id")
    val locationsId: String,
    @SerialName("measured_from")
    val measuredFrom: String,
    @SerialName("measured_to")
    val measuredTo: String,
    @SerialName("measurement_count")
    val measurementCount: Int,
) : ResponseData
