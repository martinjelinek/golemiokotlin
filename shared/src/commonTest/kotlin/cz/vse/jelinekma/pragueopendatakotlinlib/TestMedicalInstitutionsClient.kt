package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.MedicalInstitutionsClient
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalGroup
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestMedicalInstitutionsClient : TestClient() {

    private lateinit var client: MedicalInstitutionsClient

    @BeforeTest
    fun setUp() {
        client = MedicalInstitutionsClient(apiKey)
    }

    @Test
    fun testGetAllMedicalInstitutions() = runTest {
        val testData = client.getAllMedicalInstitutions(
            latlng,
            50000,
            listOf("praha-4"),
            MedicalGroup.PHARMACIES,
            limit,
            offset,
            updatedSince
        )

        assertTrue { testData.isNotEmpty() }
    }

    @Test
    fun getMedicalInstitutionById() = runTest {
        val testData = client.getMedicalInstitutionById("53279-dopravni-podnik-hl-m-prahy-as-prakticky-lekar-a-rehabilitace")

        assertEquals(
            testData.properties?.name,
            "Dopravní podnik hl. m. Prahy a.s., Praktický lékař a rehabilitace"
        )
    }
}
