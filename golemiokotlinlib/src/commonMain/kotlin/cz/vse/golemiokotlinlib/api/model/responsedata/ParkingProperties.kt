package cz.vse.golemiokotlinlib.api.model.responsedata

import kotlinx.serialization.Serializable

@Serializable
data class PlaygroundProperties(
    val id: Int,
    val description: String? = null,
): ResponseData
