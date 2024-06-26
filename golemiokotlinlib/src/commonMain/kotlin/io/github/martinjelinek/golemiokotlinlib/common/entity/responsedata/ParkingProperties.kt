package io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata

import kotlinx.serialization.Serializable

@Serializable
data class PlaygroundProperties(
    val id: Int,
    val description: String? = null,
): ResponseData
