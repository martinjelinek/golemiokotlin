package io.github.jelinekma.pragueopendatakotlinlib.v2

import io.github.martinjelinek.golemiokotlinlib.v2.client.MunicipalPoliceStationsClient
import io.github.jelinekma.pragueopendatakotlinlib.TestClient
import io.github.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestMunicipalPoliceStationsClient : TestClient() {

    private lateinit var client: MunicipalPoliceStationsClient

    @BeforeTest
    fun setUp() {
        client = MunicipalPoliceStationsClient(ApiKeyLocal.API_KEY)
    }

    @Test
    fun testAllPoliceStations() = runTest {
        val data = client.getAllMunicipalPoliceStations(
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
    fun testGetPoliceStationById() = runTest {
        // FIXME
        val data = client.getMunicipalPoliceStationById("2")

        assertEquals(data.properties?.id, "stare-mesto-lodecka-2")
    }
}