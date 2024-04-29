package io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.Measurement
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
data class AirQualityStationHistory(
    @SerialName("id")
    val id: String,
    @SerialName("measurement")
    val measurement: Measurement? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
) : ResponseData

