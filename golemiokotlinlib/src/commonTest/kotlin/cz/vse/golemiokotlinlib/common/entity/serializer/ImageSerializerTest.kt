package cz.vse.golemiokotlinlib.common.entity.serializer

import cz.vse.golemiokotlinlib.common.entity.serializers.Image
import cz.vse.golemiokotlinlib.common.entity.serializers.ImageSerializer
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Class for testing [ImageSerializer].
 */
class ImageSerializerTest {
    private val json = Json

    @Test
    fun `serialize and deserialize Image`() {
        // Create an instance of Image
        val expected = Image(url = "https://example.com/image.jpg", mimetype = "image/jpeg", size = 1024)

        // Serialize the Image object to JSON string
        val jsonString = json.encodeToString(ImageSerializer, expected)

        // Deserialize the JSON string back to Image object
        val actual = json.decodeFromString<Image>(jsonString)

        // Check if the deserialized Image object is equal to the original one
        assertEquals(expected, actual)
    }

    @Test
    fun `serialize and deserialize Image with null values`() {
        // Create an instance of Image with null values
        val image = Image()

        // Serialize the Image object to JSON string
        val jsonString = json.encodeToString(ImageSerializer, image)

        // Deserialize the JSON string back to Image object
        val deserializedImage = json.decodeFromString<Image>(jsonString)

        // Check if the deserialized Image object is equal to the original one
        assertEquals(image, deserializedImage)
    }
}
