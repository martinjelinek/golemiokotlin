package cz.vse.jelinekma.pragueopendatakotlinlib

class TestMedicalInstitutionsClient : TestClient() {

    //    // region medical-institutions
//    @Test
//    fun testGetAllMedicalInstitutions() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            range = 5000,
//            districts = listOf("praha-4"),
//            group = MedicalGroup.HEALTH_CARE,
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllMedicalInstitutions(
//            params = params
//        )
//    }
//    @Test
//    fun getMedicalInstitutionById() = runTest {
//        // todo zminit ze v golemiu nabizi blby id na testovani
//        // todo spatny ID vraci chybu - podchytit, mozna staci jen vratit null - content type je totiz null
//        val data = client.getMedicalInstitutionById("53279-dopravni-podnik-hl-m-prahy-as-prakticky-lekar-a-rehabilitace")
//    }
//    // endregion
}