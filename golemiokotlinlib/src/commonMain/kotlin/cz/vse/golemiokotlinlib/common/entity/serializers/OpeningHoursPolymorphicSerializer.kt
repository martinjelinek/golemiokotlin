package cz.vse.golemiokotlinlib.common.entity.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
open class OpeningHour(
    @SerialName("day_of_week")
    val dayOfWeek: String? = null,
    val opens: String? = null,
    val closes: String? = null,
    val description: String? = null
)

/**
 * In some cases, the json contains opening hours
 * in polymorphic format. For that case, custom serializer
 * [OpeningHoursPolymorphicSerializer] is needed.
 */
@Serializable(with = OpeningHoursPolymorphicSerializer::class)
open class OpeningHourPolymorphic(
    @SerialName("day_of_week")
    val dayOfWeek: String? = null,
    val opens: String? = null,
    val closes: String? = null,
    val description: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OpeningHourPolymorphic) return false

        if (closes != other.closes) return false
        if (dayOfWeek != other.dayOfWeek) return false
        if (opens != other.opens) return false
        return description == other.description
    }

    override fun hashCode(): Int {
        var result = closes?.hashCode() ?: 0
        result = 31 * result + (dayOfWeek?.hashCode() ?: 0)
        result = 31 * result + (opens?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }
}

object OpeningHoursPolymorphicSerializer : KSerializer<OpeningHourPolymorphic> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("OpeningHours") {
        element("day_of_week", String.serializer().descriptor)
        element("opens", String.serializer().descriptor)
        element("closes", String.serializer().descriptor)
        element("description", String.serializer().descriptor)
    }

    override fun serialize(encoder: Encoder, value: OpeningHourPolymorphic) {
        val composite = encoder.beginStructure(descriptor)
        value.dayOfWeek?.let { composite.encodeStringElement(descriptor, 0, it) }
        value.opens?.let { composite.encodeStringElement(descriptor, 1, it) }
        value.closes?.let { composite.encodeStringElement(descriptor, 2, it) }
        value.description?.let { composite.encodeStringElement(descriptor, 3, it) }
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): OpeningHourPolymorphic {
        val composite = decoder.beginStructure(descriptor)
        var dayOfWeek: String? = null
        var opens: String? = null
        var closes: String? = null
        var description: String? = null

        loop@ while (true) {
            when (val index = composite.decodeElementIndex(descriptor)) {
                CompositeDecoder.DECODE_DONE -> break@loop
                0 -> dayOfWeek = composite.decodeStringElement(descriptor, index)
                1 -> opens = composite.decodeStringElement(descriptor, index)
                2 -> closes = composite.decodeStringElement(descriptor, index)
                3 -> description = composite.decodeStringElement(descriptor, index)
                else -> throw SerializationException("Unknown index $index")
            }
        }

        composite.endStructure(descriptor)
        return OpeningHourPolymorphic(
            dayOfWeek = dayOfWeek,
            opens = opens,
            closes = closes,
            description = description
        )
    }
}
