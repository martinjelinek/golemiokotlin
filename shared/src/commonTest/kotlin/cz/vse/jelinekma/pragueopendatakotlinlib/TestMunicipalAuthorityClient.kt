package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.MunicipalAuthoritiesClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests municipal authorities client.
 */
class TestMunicipalAuthorityClient : TestClient() {

    private lateinit var client: MunicipalAuthoritiesClient

    @BeforeTest
    fun setUp() {
        client = MunicipalAuthoritiesClient(apiKey)
    }

    @Test
    fun testGetAllMunicipalAuthorities() = runTest {
        val testData = client.getAllMunicipalAuthorities(
            latlng,
            50000,
            null,
            null,
            limit,
            offset,
            updatedSince
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun getMunicipalAuthorityById() = runTest {
        val id = "magistrat-hlavniho-mesta-prahy"
        val data = client.getMunicipalAuthorityById(id)

        assertEquals(data.properties?.id, id)
    }

    @Test
    fun getMunicipalAuthoritiesQueues() = runTest {
        val id = "skoduv-palac"
        val testData = client.getMunicipalAuthoritiesQueues(id)

        assertEquals(testData.properties?.id, id)
    }
}
