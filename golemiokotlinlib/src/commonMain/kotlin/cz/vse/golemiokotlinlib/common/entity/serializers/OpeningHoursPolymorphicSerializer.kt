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
    val closes: String? = null,
    @SerialName("day_of_week")
    val dayOfWeek: String? = null,
    val opens: String? = null,
    val description: String? = null
)

/**
 * In some cases, the json contains opening hours
 * in polymorphic format. For that case, custom serializer
 * [OpeningHoursPolymorphicSerializer] is needed.
 */
@Serializable(with = OpeningHoursPolymorphicSerializer::class)
open class OpeningHourPolymorphic(
    val closes: String? = null,
    @SerialName("day_of_week")
    val dayOfWeek: String? = null,
    val opens: String? = null,
    val description: String? = null
)

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
        lateinit var dayOfWeek: String
        lateinit var opens: String
        lateinit var closes: String
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
        return OpeningHourPolymorphic(dayOfWeek, opens, closes, description)
    }
}
