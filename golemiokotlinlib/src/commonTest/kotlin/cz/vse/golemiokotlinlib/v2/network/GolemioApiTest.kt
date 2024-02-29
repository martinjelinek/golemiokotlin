package cz.vse.golemiokotlinlib.v2.network

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

    private val apiKey: String = ApiKeyLocal.API_KEY
    private val latlng = Pair("50.124935", "14.457204")
    private val from = "2023-05-16T04:27:58.000Z"
    private val to = "2023-05-17T04:27:58.000Z"
    private val updatedSince = "2023-05-18T07:38:37.000Z"
    private val range = 5000
    private val limit = 2
    private val offset = 2

    @BeforeTest
    fun setup() {
        golemioApi = GolemioApi(ApiKeyLocal.API_KEY)
    }

    @Test
    fun testGetAllAirQualityStationsURL() = runBlocking {
//        httpClient.
    }
}