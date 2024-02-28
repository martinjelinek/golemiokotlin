package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.CityDistrictsClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

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
        client.getAllCityDistricts(
            latlng = latlng,
            limit = limit,
            offset = offset,
            range = range,
            updatedSince = updatedSince,
        )
    }

    @Test
    fun getCityDistrictByID() = runTest {
        client.getCityDistrictById("praha-1")
    }
}
