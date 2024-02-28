package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.MedicalInstitutionsClient
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestMedicalInstitutionsClient : TestClient() {

    private lateinit var client: MedicalInstitutionsClient

    @BeforeTest
    fun setUp() {
        client = MedicalInstitutionsClient(apiKey)
    }

    @Test
    fun testGetAllMedicalInstitutions() = runTest {

    }
    @Test
    fun getMedicalInstitutionById() = runTest {
        // todo zminit ze v golemiu nabizi blby id na testovani
        // todo spatny ID vraci chybu - podchytit, mozna staci jen vratit null - content type je totiz null
        val data = client.getMedicalInstitutionById("53279-dopravni-podnik-hl-m-prahy-as-prakticky-lekar-a-rehabilitace")
    }
}