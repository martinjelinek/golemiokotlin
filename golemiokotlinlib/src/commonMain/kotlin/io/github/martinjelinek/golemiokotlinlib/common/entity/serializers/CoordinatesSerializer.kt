package io.github.martinjelinek.golemiokotlinlib.common.entity.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class Geometry(
    @Serializable(with = CoordinatesSerializer::class)
    val coordinates: Coordinates? = null,
    val type: String? = null
)

@Serializable
sealed class Coordinates {
    abstract val value: Any?
}

@Serializable
data class SingleCoordinates(
    override val value: List<Double?>?
) : Coordinates()

@Serializable
data class DoubleCoordinates(
    override val value: List<List<Double?>?>?
) : Coordinates()

@Serializable
data class TripleCoordinates(
    override val value: List<List<List<Double>>?>
) : Coordinates()

@Serializable
data class QuadrupleCoordinates(
    override val value: List<List<List<List<Double>>?>>
) : Coordinates()

/**
 * Needed because there are three types of [Coordinates].
 *
 * Type "Point" -> [SingleCoordinates], [DoubleCoordinates]
 * Type "Polygon" -> [TripleCoordinates]
 * Type "MultiPolygon" -> [QuadrupleCoordinates]
 */
object CoordinatesSerializer : KSerializer<Coordinates> {
    override val descriptor: SerialDescriptor = SingleCoordinates.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Coordinates) {
        when (value) {
            is SingleCoordinates -> encoder.encodeSerializableValue(
                SingleCoordinates.serializer(),
                value
            )

            is DoubleCoordinates -> encoder.encodeSerializableValue(
                DoubleCoordinates.serializer(),
                value
            )

            is TripleCoordinates -> encoder.encodeSerializableValue(
                TripleCoordinates.serializer(),
                value
            )

            is QuadrupleCoordinates -> encoder.encodeSerializableValue(
                QuadrupleCoordinates.serializer(),
                value
            )
        }
    }

    override fun deserialize(decoder: Decoder): Coordinates {
        return when (val json = decoder.decodeSerializableValue(JsonElement.serializer())) {
            is JsonArray -> {
                when (json.firstOrNull()) {
                    is JsonPrimitive -> {
                        val coordinates = json.map { it.jsonPrimitive.doubleOrNull }
                        SingleCoordinates(coordinates)
                    }

                    is JsonArray -> {
                        if (json.all { it is JsonArray }) {
                            when (getNumberOfLevels(json)) {
                                2 -> {
                                    val coordinates = parseDoubleCoordinates(json)
                                    DoubleCoordinates(coordinates)
                                }

                                3 -> {
                                    val coordinates = parseTripleCoordinates(json)
                                    TripleCoordinates(coordinates)
                                }

                                4 -> {
                                    val coordinates = parseQuadrupleCoordinates(json)
                                    QuadrupleCoordinates(coordinates)
                                }

                                else -> throw SerializationException("Invalid coordinates JSON structure")
                            }
                        } else {
                            val coordinates = parseQuadrupleCoordinates(json)
                            QuadrupleCoordinates(coordinates)
                        }
                    }

                    else -> throw SerializationException("Invalid coordinates JSON structure")
                }
            }

            is JsonObject -> {
                when (val type = json["type"]?.jsonPrimitive?.contentOrNull) {
                    "Point" -> {
                        val coordinates = listOf(json["coordinates"]?.jsonPrimitive?.double)
                        SingleCoordinates(coordinates)
                    }

                    "Polygon" -> decoder.decodeSerializableValue(TripleCoordinates.serializer())
                    "MultiPolygon" -> decoder.decodeSerializableValue(QuadrupleCoordinates.serializer())
                    else -> throw SerializationException("Invalid geometry type: $type")
                }
            }

            else -> throw SerializationException("Invalid coordinates JSON structure")
        }
    }

    private fun parseDoubleCoordinates(jsonArray: JsonArray): List<List<Double>> {
        return jsonArray.map { subElement ->
            val nestedArray = subElement.jsonArray
            nestedArray.map {
                it.jsonPrimitive.doubleOrNull ?: error("Invalid coordinates JSON structure")
            }
        }
    }

    private fun parseTripleCoordinates(jsonArray: JsonArray): List<List<List<Double>>> {
        return jsonArray.map { element ->
            val subArray = element.jsonArray
            subArray.map { subElement ->
                val nestedArray = subElement.jsonArray
                nestedArray.map {
                    it.jsonPrimitive.doubleOrNull ?: error("Invalid coordinates JSON structure")
                }
            }
        }
    }

    private fun parseQuadrupleCoordinates(jsonArray: JsonArray): List<List<List<List<Double>>>> {
        return jsonArray.map { element ->
            val subArray = element.jsonArray
            subArray.map { subElement ->
                subElement.jsonArray.map { subSubElement ->
                    val nestedArray = subSubElement.jsonArray
                    nestedArray.map {
                        it.jsonPrimitive.doubleOrNull ?: error("Invalid coordinates JSON structure")
                    }
                }
            }
        }
    }

    private fun getNumberOfLevels(jsonArray: JsonArray): Int {
        var levels = 0

        fun traverse(json: JsonElement, currentLevel: Int) {
            if (json is JsonArray) {
                levels = maxOf(levels, currentLevel)
                json.forEach { traverse(it, currentLevel + 1) }
            }
        }
        traverse(jsonArray, 1)

        return levels
    }
}
