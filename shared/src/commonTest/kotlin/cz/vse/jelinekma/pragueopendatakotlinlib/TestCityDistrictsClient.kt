package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.CityDistrictsClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test class for city districts client.
 */
class TestCityDistrictsClient : TestClient() {

    private lateinit var client: CityDistrictsClient

    @BeforeTest
    fun setUp() {
        client = CityDistrictsClient(apiKey)
    }

    @Test
    fun testGetAllCityDistricts() = runTest {
        val testData = client.getAllCityDistricts(
            latlng = latlng,
            limit = limit,
            offset = offset,
            range = range,
            updatedSince = null,
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun getCityDistrictByID() = runTest {
        val testData = client.getCityDistrictById("praha-1")

        assertTrue { testData.properties?.name is String }
        assertEquals(testData.properties?.name, "Praha 1")

    }
}
