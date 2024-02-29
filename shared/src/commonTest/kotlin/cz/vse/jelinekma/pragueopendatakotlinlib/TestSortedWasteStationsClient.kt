package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.SortedWasteStationsClient
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class TestSortedWasteStationsClient : TestClient() {
    private lateinit var client: SortedWasteStationsClient
    private val id = "1"
    private val ksnkoId = "15288"

    @BeforeTest
    fun setUp() {
        client = SortedWasteStationsClient(ApiKeyLocal.API_KEY)
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