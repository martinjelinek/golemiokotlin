package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.MunicipalAuthoritiesClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * Tests municipal authorities client.
 */
class TestMunicipalAuthorityClient : TestClient() {

    private lateinit var client: MunicipalAuthoritiesClient

    @BeforeTest
    fun setUp() {
        client = MunicipalAuthoritiesClient(apiKey)
    }

    @Test
    fun testGetAllMunicipalAuthorities() = runTest {

    }

    @Test
    fun getMunicipalAuthorityById() = runTest {
        // todo zminit ze v golemiu nabizi blby id na testovani
        // todo spatny ID vraci chybu - podchytit, mozna staci jen vratit null - content type je totiz null
        val data = client.getMunicipalAuthorityById("urad-mestske-casti-praha-6")
    }
    // endregion
}
