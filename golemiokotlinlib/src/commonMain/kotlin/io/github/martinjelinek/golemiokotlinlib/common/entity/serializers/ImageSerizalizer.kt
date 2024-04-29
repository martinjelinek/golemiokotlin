package io.github.martinjelinek.golemiokotlinlib.common.entity.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ImageSerializer::class)
open class Image(
    val url: String? = null,
    val mimetype: String? = null,
    val size: Int? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Image) return false

        if (url != other.url) return false
        if (mimetype != other.mimetype) return false
        return size == other.size
    }

    override fun hashCode(): Int {
        var result = url?.hashCode() ?: 0
        result = 31 * result + (mimetype?.hashCode() ?: 0)
        result = 31 * result + (size ?: 0)
        return result
    }
}

object ImageSerializer : KSerializer<Image> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("Image") {
            element("url", String.serializer().descriptor)
            element("mimetype", String.serializer().descriptor)
            element("size", String.serializer().descriptor)
        }

    override fun serialize(encoder: Encoder, value: Image) {
        val composite = encoder.beginStructure(descriptor)
        value.url?.let { composite.encodeStringElement(descriptor, 0, it) }
        value.mimetype?.let { composite.encodeStringElement(descriptor, 1, it) }
        value.size?.let { composite.encodeIntElement(descriptor, 2, it) }
        composite.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): Image {
        val composite = decoder.beginStructure(descriptor)
        var url: String? = null
        var mimetype: String? = null
        var size: Int? = null

        loop@ while (true) {
            when (val index = composite.decodeElementIndex(descriptor)) {
                CompositeDecoder.DECODE_DONE -> break@loop
                0 -> url = composite.decodeStringElement(descriptor, index)
                1 -> mimetype = composite.decodeStringElement(descriptor, index)
                2 -> size = composite.decodeIntElement(descriptor, index)
                else -> throw SerializationException("Unknown index $index")
            }
        }

        composite.endStructure(descriptor)
        return Image(url, mimetype, size)
    }
}
