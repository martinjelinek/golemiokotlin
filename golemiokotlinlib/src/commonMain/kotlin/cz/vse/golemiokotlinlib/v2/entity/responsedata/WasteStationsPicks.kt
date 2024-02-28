package cz.vse.golemiokotlinlib.v2.entity.responsedata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WasteStationsPicks(
    val id: String,
    @SerialName("pick_minfilllevel")
    val pickMinFillLevel: Int? = null,
    val decrease: Int,
    @SerialName("sensor_code")
    val sensorCode: String? = null,
    @SerialName("pick_at_utc")
    val pickAtUtc: String,
    @SerialName("percent_before")
    val percentBefore: Int? = null,
    @SerialName("percent_now")
    val percentNow: Int? = null,
    @SerialName("event_driven")
    val eventDriven: Boolean? = null,
    @SerialName("updated_at")
    val updatedAt: Long? = null
): ResponseData

@Serializable
data class WasteStationsPickDays(
    @SerialName("ksnko_id")
    val ksnkoId: Int? = null,
    @SerialName("sensoneo_code")
    val sensoneoCode: String? = null,
    @SerialName("generated_dates")
    val generatedDates: List<String>? = null
): ResponseData
