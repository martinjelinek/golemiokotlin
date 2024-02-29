package cz.vse.jelinekma.pragueopendatakotlinlib

import cz.vse.golemiokotlinlib.v2.client.MedicalInstitutionsClient
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalGroup
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalInstitutionTypes
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

    @Test
    fun getMedicalInstitutionsTypesTest() = runTest {
        val testData = client.getMedicalInstitutionTypes()

        val expectedDate = MedicalInstitutionTypes(
            listOf(
                "Nemocniční lékárna s odbornými pracovišti",
                "Lékárna",
                "Lékárna s odbornými pracovišti",
                "Výdejna",
                "Vojenská lékárna",
                "Vojenská lékárna s OOVL",
                "Nemocniční lékárna",
                "Lékárna s odbornými pracovišti s OOVL",
                "Nemocniční lékárna s OOVL",
                "Nemocniční lékárna s odbor. pracovišti s OOVL",
                "Odloučené oddělení výdeje léčivých přípravků",
                "Lékárna s OOVL",
                "Lékárna s odborným pracovištěm, která zásobuje lůžková zdravotnická zařízení",
                "Lékárna s odborným pracovištěm, která zásobuje lůžková zdravotnická zařízení s OOVL",
                "Lékárna zásobujicí zařízení ústavní péče",
                "Lékárna zásobujicí zařízení ústavní péče s OOVL"
            ),
            listOf(
                "Fakultní nemocnice",
                "Nemocnice",
                "Ostatní ambulantní zařízení",
                "Ostatní zdravotnická zařízení",
                "Zdravotní záchranná služba",
                "Zdravotnické středisko"
            ),
        )

        assertEquals(testData, expectedDate)
    }
}
