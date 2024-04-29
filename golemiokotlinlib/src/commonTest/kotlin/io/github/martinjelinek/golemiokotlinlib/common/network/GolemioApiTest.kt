package io.github.martinjelinek.golemiokotlinlib.common.network

import io.ktor.client.HttpClient
import io.ktor.http.URLBuilder
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class GolemioApiTest {

    lateinit var httpClient: HttpClient
    private val productQueryUrl = with(URLBuilder(GolemioApi.API_URL)) {
        build()
    }

    private lateinit var golemioApi: IGolemioApi

    @BeforeTest
    fun setup() {
        golemioApi = GolemioApi(ApiKeyLocal.API_KEY)
    }

    @Test
    fun testGetAllAirQualityStationsURL() = runBlocking {
    }
}