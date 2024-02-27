package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.BicycleCountersClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestBicycleCountersClient : TestClient() {

    private lateinit var client: BicycleCountersClient

    @BeforeTest
    fun setUp() {
        client = BicycleCountersClient(apiKey)
    }

    @Test
    fun testGetAllBicycleCounters() = runTest {
        client.getAllBicycleCounters(
            latlng = latlng,
            limit = limit,
            offset = offset,
            range = range,
        )
    }

    @Test
    fun testGetBicycleCounterDetections() = runTest {
        client.getBicycleCountersDetections(
            limit = limit,
            offset = offset,
            from = from,
            to = to,
            aggregate = false,
            id = "camea-BC_ZA-BO"
        )
    }

    @Test
    fun testGetBicycleCounterTemperature() = runTest {
        client.getBicycleCountersTemperatures(
            limit = limit,
            offset = offset,
            from = from,
            to = to,
            aggregate = false,
            ids = null
        )
    }
}
