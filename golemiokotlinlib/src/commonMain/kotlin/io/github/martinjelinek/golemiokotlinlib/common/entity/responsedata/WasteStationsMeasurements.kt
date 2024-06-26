package io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WasteStationMeasurements(
    val id: String,
    @SerialName("sensor_code")
    val sensorCode: String,
    @SerialName("percent_calculated")
    val percentCalculated: Int,
    val upturned: Int,
    val temperature: Int,
    @SerialName("battery_status")
    val batteryStatus: Double,
    @SerialName("measured_at_utc")
    val measuredAtUtc: String,
    @SerialName("prediction_utc")
    val predictionUtc: String,
    val firealarm: Int,
    @SerialName("updated_at")
    val updatedAt: Long
): ResponseData

enum class WasteStationAccessibility(val accessibility: Int) {
    ACCESSIBLE(1),
    HOUSE_RESIDENTS_ONLY(2),
    UNKNOWN(3)
}