package cz.vse.golemiokotlinlib.common.entity.serializer

/**
 * Class for testing [OpeningHoursPolymorphicSerializer].
 */
import cz.vse.golemiokotlinlib.common.entity.serializers.OpeningHourPolymorphic
import cz.vse.golemiokotlinlib.common.entity.serializers.OpeningHoursPolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class OpeningHoursPolymorphicSerializerTest {
    private val json = Json

    @Test
    fun `deserialize OpeningHourPolymorphic from JSON`() {
        // Define a JSON string representing an OpeningHourPolymorphic object
        val jsonString = """
            {
                "day_of_week": "Monday",
                "opens": "08:00",
                "closes": "17:00",
                "description": "Regular opening hours"
            }
        """

        // Deserialize the JSON string to an OpeningHourPolymorphic object
        val deserializedOpeningHourPolymorphic = json.decodeFromString<OpeningHourPolymorphic>(jsonString)

        // Assert that the deserialized object has the expected values
        assertEquals("Monday", deserializedOpeningHourPolymorphic.dayOfWeek)
        assertEquals("08:00", deserializedOpeningHourPolymorphic.opens)
        assertEquals("17:00", deserializedOpeningHourPolymorphic.closes)
        assertEquals("Regular opening hours", deserializedOpeningHourPolymorphic.description)
    }

    @Test
    fun `serialize and deserialize OpeningHourPolymorphic`() {
        // Create an instance of OpeningHourPolymorphic
        val openingHourPolymorphic = OpeningHourPolymorphic(
            dayOfWeek = "Monday",
            opens = "08:00",
            closes = "17:00",
            description = "Regular opening hours"
        )

        // Serialize the OpeningHourPolymorphic object to JSON string
        val jsonString = json.encodeToString(OpeningHoursPolymorphicSerializer, openingHourPolymorphic)

        println("Serialized JSON: $jsonString")

        // Deserialize the JSON string back to OpeningHourPolymorphic object
        val deserializedOpeningHourPolymorphic = json.decodeFromString(OpeningHoursPolymorphicSerializer, jsonString)

        println("Deserialized object: $deserializedOpeningHourPolymorphic")

        println("expected .dayOfWeek: ${openingHourPolymorphic.dayOfWeek}")
        println("expected .opens: ${openingHourPolymorphic.opens}")
        println("expected .closes: ${openingHourPolymorphic.closes}")
        println("expected .description: ${openingHourPolymorphic.description}")

        println("actual .dayOfWeek: ${deserializedOpeningHourPolymorphic.dayOfWeek}")
        println("actual .opens: ${deserializedOpeningHourPolymorphic.opens}")
        println("actual .closes: ${deserializedOpeningHourPolymorphic.closes}")
        println("actual .description: ${deserializedOpeningHourPolymorphic.description}")

        // Check if the deserialized OpeningHourPolymorphic object is equal to the original one
        assertEquals(openingHourPolymorphic, deserializedOpeningHourPolymorphic)
    }

    @Test
    fun `serialize and deserialize OpeningHourPolymorphic with null values`() {
        // Create an instance of OpeningHourPolymorphic with null values
        val openingHourPolymorphic = OpeningHourPolymorphic()

        // Serialize the OpeningHourPolymorphic object to JSON string
        val jsonString = json.encodeToString(OpeningHoursPolymorphicSerializer, openingHourPolymorphic)

        // Deserialize the JSON string back to OpeningHourPolymorphic object
        val deserializedOpeningHourPolymorphic = json.decodeFromString(OpeningHoursPolymorphicSerializer, jsonString)

        // Check if the deserialized OpeningHourPolymorphic object is equal to the original one
        assertEquals(openingHourPolymorphic, deserializedOpeningHourPolymorphic)
    }
}
