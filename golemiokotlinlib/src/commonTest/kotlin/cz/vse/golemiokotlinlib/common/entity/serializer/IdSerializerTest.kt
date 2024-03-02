package cz.vse.golemiokotlinlib.common.entity.serializer

import cz.vse.golemiokotlinlib.common.entity.serializers.IdObjectSerializer
import cz.vse.golemiokotlinlib.common.entity.serializers.IntId
import cz.vse.golemiokotlinlib.common.entity.serializers.StringId
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * Class for tests of [IdObjectSerializer].
 */
class IdObjectSerializerTest {
    private val json = Json

    @Test
    fun `serialize StringId`() {
        val stringId = StringId("test")
        val serialized = json.encodeToString(IdObjectSerializer, stringId)
        assertEquals("\"test\"", serialized)
    }

    @Test
    fun `serialize IntId`() {
        val intId = IntId(42)
        val serialized = json.encodeToString(IdObjectSerializer, intId)
        assertEquals("42", serialized)
    }

    @Test
    fun `deserialize StringId`() {
        val deserialized = json.decodeFromString(IdObjectSerializer, "\"test\"")
        assertEquals(expected = StringId("test"), actual = deserialized)
    }

    @Test
    fun `deserialize IntId`() {
        val deserialized = json.decodeFromString(IdObjectSerializer, "42")
        assertEquals(IntId(42), deserialized)
    }

    @Test
    fun `deserialize invalid JSON structure`() {
        assertFailsWith<SerializationException> {
            json.decodeFromString(IdObjectSerializer, "{}")
        }
    }
}
