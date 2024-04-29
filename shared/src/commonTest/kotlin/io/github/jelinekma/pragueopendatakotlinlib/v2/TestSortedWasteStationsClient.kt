package io.github.jelinekma.pragueopendatakotlinlib.v2

import io.github.martinjelinek.golemiokotlinlib.v2.client.WasteCollectionClient
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.WasteStationAccessibility
import io.github.jelinekma.pragueopendatakotlinlib.TestClient
import io.github.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class TestSortedWasteStationsClient : TestClient() {
    private lateinit var client: WasteCollectionClient
    private val id = "1"
    private val ksnkoId = "15288"

    @BeforeTest
    fun setUp() {
        client = WasteCollectionClient(ApiKeyLocal.API_KEY)
    }

    @Test
    fun testAllWasteStations() = runTest {
        val data = client.getSortedWasteStations(
            latlng,
            null,
            null,
            WasteStationAccessibility.ACCESSIBLE,
            limit,
            null,
            false,
            id,
            ksnkoId
        )

        assertTrue { data.isNotEmpty() }
    }

    @Test
    fun testGetSortedWasteStationsPickDays() = runTest {
        val testData = client.getSortedWasteStationsPickDays(
            ksnkoId,
            ksnkoId
        )

        assertTrue { testData.isNotEmpty() }
    }
}