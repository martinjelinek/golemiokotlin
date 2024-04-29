package io.github.martinjelinek.golemiokotlinlib.common.entity.serializer

import io.github.martinjelinek.golemiokotlinlib.common.entity.serializers.Coordinates
import io.github.martinjelinek.golemiokotlinlib.common.entity.serializers.CoordinatesSerializer
import io.github.martinjelinek.golemiokotlinlib.common.entity.serializers.QuadrupleCoordinates
import io.github.martinjelinek.golemiokotlinlib.common.entity.serializers.TripleCoordinates
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * Class for testing [CoordinatesSerializer].
 */
class CoordinatesSerializerTest {

    private val json = Json

    @Test
    fun `deserialize TripleCoordinates from JSON`() {
        // Define a JSON string representing triple coordinates
        val jsonString = """[[[10.0, 20.0], [30.0, 40.0]], [[50.0, 60.0], [70.0, 80.0]]]"""

        // Deserialize the JSON string to TripleCoordinates
        val coordinates = json.decodeFromString(CoordinatesSerializer, jsonString)

        // Assert that the deserialized coordinates have the correct values
        assertEquals(
            TripleCoordinates(
                listOf(
                    listOf(listOf(10.0, 20.0), listOf(30.0, 40.0)),
                    listOf(listOf(50.0, 60.0), listOf(70.0, 80.0))
                )
            ),
            coordinates
        )
    }

    @Test
    fun `deserialize QuadrupleCoordinates from JSON`() {
        // Define a JSON string representing quadruple coordinates
        val jsonString =
            """[[[[10.0, 20.0], [30.0, 40.0]], [[50.0, 60.0], [70.0, 80.0]]], [[[90.0, 100.0], [110.0, 120.0]]]]"""

        // Deserialize the JSON string to QuadrupleCoordinates
        val coordinates = json.decodeFromString(CoordinatesSerializer, jsonString)

        // Assert that the deserialized coordinates have the correct values
        assertEquals(
            QuadrupleCoordinates(
                listOf(
                    listOf(
                        listOf(listOf(10.0, 20.0), listOf(30.0, 40.0)),
                        listOf(listOf(50.0, 60.0), listOf(70.0, 80.0))
                    ),
                    listOf(listOf(listOf(90.0, 100.0), listOf(110.0, 120.0)))
                )
            ),
            coordinates
        )
    }

    @Test
    fun `deserialize unsupported geometry type throws SerializationException`() {
        // Define a JSON string representing unsupported geometry type
        val jsonString = """{"type": "UnsupportedType", "coordinates": [[10.0, 20.0], [30.0, 40.0]]}"""

        // Deserialize the JSON string and expect SerializationException
        assertFailsWith<SerializationException> {
            json.decodeFromString<Coordinates>(jsonString)
        }
    }
}
