package io.github.jelinekma.pragueopendatakotlinlib.v2

import io.github.martinjelinek.golemiokotlinlib.v2.client.MunicipalLibrariesClient
import io.github.jelinekma.pragueopendatakotlinlib.TestClient
import io.github.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestMunicipalLibrariesClient : TestClient() {

    private lateinit var client: MunicipalLibrariesClient

    @BeforeTest
    fun setUp() {
        client = MunicipalLibrariesClient(ApiKeyLocal.API_KEY)
    }

    @Test
    fun testAllMunicipalLibraries() = runTest {
        val data = client.getAllMunicipalLibraries(
            latlng,
            range,
            null,
            limit,
            offset,
            updatedSince
        )

        assertTrue { data.isNotEmpty() }
    }

    @Test
    fun getMunicipalLibraryById() = runTest {
        val data = client.getMunicipalLibraryById("2")

        assertEquals(data.properties?.name, "Barrandov")
    }
}
