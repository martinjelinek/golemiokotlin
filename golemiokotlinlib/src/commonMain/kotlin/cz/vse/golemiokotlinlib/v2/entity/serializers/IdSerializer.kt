package cz.vse.golemiokotlinlib.v2.entity.serializers

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
sealed class IdObject {
    abstract val value: Any?
}

@Serializable
data class StringId(
    override val value: String
) : IdObject()

@Serializable
data class IntId(
    override val value: Int
) : IdObject()

/**
 * Serializer for [IdObject].
 *
 * Needed because some of the responses object (such as [ParkingV1Properties])
 * have the id in schema type set as "oneOf string/int".
 * TODO do reportu
 */
object IdObjectSerializer : KSerializer<IdObject> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("IdObject") {
            element("id", String.serializer().descriptor)
        }

    override fun serialize(encoder: Encoder, value: IdObject) {
        when (value) {
            is StringId -> encoder.encodeString(value.value)
            is IntId -> encoder.encodeInt(value.value)
        }
    }

    override fun deserialize(decoder: Decoder): IdObject {
        return when (val json = decoder.decodeSerializableValue(JsonElement.serializer())) {
            is JsonPrimitive -> {
                if (json.isString) {
                    StringId(json.jsonPrimitive.toString())
                } else {
                    IntId(json.jsonPrimitive.int)
                }
            }
            else -> throw SerializationException("Invalid id JSON structure")
        }
    }
}
