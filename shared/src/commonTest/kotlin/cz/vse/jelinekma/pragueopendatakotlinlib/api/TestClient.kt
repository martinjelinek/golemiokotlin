package cz.vse.jelinekma.pragueopendatakotlinlib.api

import cz.vse.golemiokotlinlib.v2.service.impl.RemoteRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * Class for testing all API requests.
 * TODO adjust tests to actually test something, now it's just verifying that the data gets fetched
 * TODO test on iOS
 */
class TestClient {

    private lateinit var client: RemoteRepository
    private val xAccessToken =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6Im10LmplbGluZWtAZ21haWwuY29tIiwiaWQiOjIwMjksIm5hbWUiOm51bGwsInN1cm5hbWUiOm51bGwsImlhdCI6MTY4OTE4MzYyNCwiZXhwIjoxMTY4OTE4MzYyNCwiaXNzIjoiZ29sZW1pbyIsImp0aSI6ImQ4NDVlNmE0LWRlNDAtNGEyYS1iMDcwLTVmYTRhYjRhZTE3NSJ9.yvqnXrmiBWLSiVFW5hAMhOs12umOkZPOhekiQTN_JEo"
    private val latlng = Pair("50.124935", "14.457204")
    private val from = "2023-05-16T04:27:58.000Z"
    private val to = "2023-05-16T04:27:58.000Z"
    private val updatedSince = "2023-05-18T07:38:37.000Z"

    @BeforeTest
    fun setUp() {
        client = RemoteRepository()
    }

    // region air-quality
    // TODO add districts param
    @Test
    fun testGetAllAirQualityStation() = runTest {
        client.getAllAirQualityStations(
            latlng = Pair("50.124935", "14.457204"),
            range = 5000,
            districts = null,
            limit = 2,
            offset = 2,
            updatedSince = updatedSince
        )
    }

    @Test
    fun testGetAirHistoryData() = runTest {
        client.getAirQualityStationsHistory(
            sensorId = "ACHOA",
            limit = 10,
            offset = 0,
            from = "2022-05-16T04:27:58.000Z",
            to = "2022-05-18T04:27:58.000Z"
        )
    }
    // endregion
    // region bicycle-counters

    @Test
    fun testGetAllBicycleCounters() = runTest {
        client.getAllBicycleCounters(
            latlng = latlng,
            limit = 10,
            offset = 0,
            range = 5000,
        )
    }

    @Test
    fun testGetBicycleCounterDetections() = runTest {
        client.getBicycleCountersDetections(
            limit = 10,
            offset = 0,
            from = from,
            to = to,
            aggregate = false,
            id = "camea-BC_ZA-BO"
        )
    }

    @Test
    fun testGetBicycleCounterTemperature() = runTest {
        client.getBicycleCountersTemperatures(
            limit = 10,
            offset = 0,
            from = from,
            to = to,
            aggregate = false,
            ids = null
        )
    }

    //endregion
    // region city-districts
    @Test
    fun testGetAllCityDistricts() = runTest {
        client.getAllCityDistricts(
            latlng = latlng,
            limit = 10,
            offset = 10,
            range = 5000,
            updatedSince = updatedSince,
        )
    }

    @Test
    fun getCityDistrictByID() = runTest {
        client.getCityDistrictById("praha-1")
    }
    // endregion
    // region gardens
    @Test
    fun testGetAllGardens() = runTest {
        client.getAllGardens(
            latlng,
            limit = 10,
            offset = 0,
            range = 5000,
            districts = listOf("praha-4"),
            updatedSince = updatedSince,
        )
    }

    @Test
    fun getGardensByID() = runTest {
        // todo zminit ze v golemiu nabizi blby id na testovani
        client.getGardenById("letenske-sady")
    }
    // endregion
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
//    // region municipal-authorities
//    @Test
//    fun testGetAllMunicipalAuthorities() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            range = 5000,
//            districts = listOf("praha-4"),
//            type = MunicipalAuthorityType.MUNICIPALITY,
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllMunicipalAuthorities(
//            params = params
//        )
//    }
//
//    @Test
//    fun getMunicipalAuthorityById() = runTest {
//        // todo zminit ze v golemiu nabizi blby id na testovani
//        // todo spatny ID vraci chybu - podchytit, mozna staci jen vratit null - content type je totiz null
//        val data = client.getMunicipalAuthorityById("urad-mestske-casti-praha-6")
//    }
//    // endregion
//    // region municipal-libraries
//    @Test
//    fun testAllMunicipalLibraries() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            range = 5000,
//            districts = listOf("praha-4"),
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllMunicipalLibraries(
//            params = params
//        )
//    }
//
//    @Test
//    fun getMunicipalLibraryById() = runTest {
//        // todo zminit ze v golemiu nabizi blby id na testovani
//        // todo spatny ID vraci chybu - podchytit, mozna staci jen vratit null - content type je totiz null
//        val data = client.getMunicipalLibraryById("2")
//    }
//    // endregion
//    // region municipal-police-stations
//    @Test
//    fun testGetAllMunicipalPoliceStations() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            range = 5000,
//            districts = listOf("praha-4"),
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllMunicipalPoliceStations(
//            params = params
//        )
//
//    }
//
//    @Test
//    fun testGetMunicipalPoliceStationById() = runTest {
//        val data = client.getMunicipalPoliceStationById("zbraslav-zabovreska-1227")
//    }
//    // endregion
//    // region parking-v1
//    @Test
//    fun testGetAllParkingV1() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            range = 5000,
//            districts = listOf("praha-4"),
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllParkingsV1(
//            params = params
//        )
//    }
//
//    @Test
//    fun testGetParingV1ById() = runTest {
//        val data = client.getParkingV1ById("534002")
//    }
//    // endregion
//    // region parking-v2
//    @Test
//    fun testGetAllParkingV2() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            source = "korid",
//            range = 5000,
//            districts = listOf("praha-4"),
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllParkingsV2(
//            params = params
//        )
//    }
//    @Test
//    fun testGetParingV2ById() = runTest {
//        val data = client.getParkingV2ById("korid-1")
//    }
//
//    // endregion
//    // region parking-v3
//    // todo
//    // endregion
//    // region playgrounds
//    @Test
//    fun testGetAllPlaygrounds() = runTest {
//        val params = GetAllRequestParams()
//        params.set(
//            limit = 10,
//            offset = 0,
//            range = 5000,
//            districts = listOf("praha-4"),
//            updatedSince = "2023-05-18T07:38:37.000Z"
//        )
//        val data = client.getAllPlaygrounds(
//            params = params
//        )
//    }
//    @Test
//    fun testGetPlaygroundById() = runTest {
//        val data = client.getPlaygroundById("370")
//    }
//    // endregion
//    // region waste-collection
//    @Test
//    fun testGetAllWasteCollection() = runTest {
//        // todo
//        val params = GetAllRequestParams()
//        val data = client.getAllWasteCollections(params)
//    }
//    // endregion
//    // region waste-yards
//    @Test
//    fun testGetAllWasteYards() = runTest {
//        val params = GetAllRequestParams()
//
//        val data = client.getAllWasteYards(params)
//    }
//
//    @Test
//    fun testGetWasteYardById() = runTest {
//        val data = client.getWasteYardById("sberny-dvur-hlavniho-mesta-prahy-malesicka-4")
//    }
//
//    // endregion
}
