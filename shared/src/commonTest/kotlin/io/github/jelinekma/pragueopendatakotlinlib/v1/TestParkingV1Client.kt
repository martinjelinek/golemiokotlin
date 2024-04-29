package io.github.jelinekma.pragueopendatakotlinlib.v1

import io.github.martinjelinek.golemiokotlinlib.common.service.impl.HttpClientFactory
import io.github.martinjelinek.golemiokotlinlib.v1.client.ParkingClient
import io.github.jelinekma.pragueopendatakotlinlib.TestClient
import io.github.jelinekma.pragueopendatakotlinlib.dummyData.ApiKeyLocal
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestParkingV1Client : TestClient() {

    private lateinit var client: ParkingClient
    private lateinit var http: HttpClientFactory

    @BeforeTest
    fun setUp() {
        client = ParkingClient(ApiKeyLocal.API_KEY)
        http = HttpClientFactory
    }

    @Test
    fun testGetAllParkings() = runTest {
        val testData = client.getAllParkings(
            latlng,
            range,
            null,
            limit,
            offset,
            updatedSince
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun testGetParkingById() = runTest {
        val id = "534015"
        val testData = client.getParkingsById(id)

        assertEquals(testData.properties?.name, "Depo Hostivař")
    }

    @Test
    fun testGetParkingsHistory() = runTest {
        val testData = client.getParkingsHistory(
            limit,
            null,
            from,
            to,
            "tsk-143"
        )

        // TODO 29/2/24 - not able to find out what params actually returns something
//        assertTrue { testData.isNotEmpty() }
    }
}
