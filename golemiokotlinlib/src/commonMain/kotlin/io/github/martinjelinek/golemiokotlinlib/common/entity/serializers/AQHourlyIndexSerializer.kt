package io.github.martinjelinek.golemiokotlinlib.common.entity.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

@Serializable
sealed class AQObject {
    abstract val value: Any?
}

@Serializable
data class StringAQ(
    override val value: String
) : AQObject() {
    override fun equals(other: Any?): Boolean {
        return other is String && value == other
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

@Serializable
data class IntAQ(
    override val value: Int
) : AQObject() {
    override fun equals(other: Any?): Boolean {
        return other is Int && value == other
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

/**
 * Serializer for [IdObject].
 *
 * Needed because some of the responses object (such as [ParkingProperties])
 * have the id in schema type set as "oneOf string/int".
 */
object AQHourlyIndexSerializer : KSerializer<AQObject> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("IdObject") {
            element("id", String.serializer().descriptor)
        }

    override fun serialize(encoder: Encoder, value: AQObject) {
        when (value) {
            is StringAQ -> encoder.encodeString(value.value)
            is IntAQ -> encoder.encodeInt(value.value)
        }
    }

    override fun deserialize(decoder: Decoder): AQObject {
        return when (val json = decoder.decodeSerializableValue(JsonElement.serializer())) {
            is JsonPrimitive -> {
                if (json.isString) {
                    StringAQ(json.jsonPrimitive.content)
                } else {
                    IntAQ(json.jsonPrimitive.int)
                }
            }
            else -> throw SerializationException("Invalid id JSON structure")
        }
    }
}
