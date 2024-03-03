package cz.vse.jelinekma.pragueopendatakotlinlib.v2

import cz.vse.golemiokotlinlib.v2.client.BicycleCountersClient
import cz.vse.jelinekma.pragueopendatakotlinlib.TestClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Test class for verifying [BicycleCountersClient] functionality.
 */
class TestBicycleCountersClient : TestClient() {

    private lateinit var client: BicycleCountersClient

    @BeforeTest
    fun setUp() {
        client = BicycleCountersClient(apiKey)
    }

    @Test
    fun testGetAllBicycleCounters() = runTest {
        val testData = client.getAllBicycleCounters(
            latlng = latlng,
            limit = limit,
            offset = offset,
            range = range,
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun testGetBicycleCounterDetections() = runTest {
        val testData = client.getBicycleCountersDetections(
            limit = limit,
            offset = offset,
            from = from,
            to = to,
            aggregate = false,
            id = "camea-BC_ZA-BO",
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun testGetBicycleCounterTemperature() = runTest {
        val testData = client.getBicycleCountersTemperatures(
            limit = limit,
            offset = offset,
            from = from,
            to = to,
            aggregate = false,
            ids = null
        )

        assertTrue { testData.isNotEmpty() }
    }
}
