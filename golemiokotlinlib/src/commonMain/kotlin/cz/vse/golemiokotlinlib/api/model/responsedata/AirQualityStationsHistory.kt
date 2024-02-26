package cz.vse.golemiokotlinlib.api.model.responsedata

import cz.vse.golemiokotlinlib.api.model.featurescollection.Measurement
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Polymorphic
data class AirQualityStationHistory(
    @SerialName("id")
    val id: String,
    @SerialName("measurement")
    val measurement: Measurement? = null
) : ResponseData

