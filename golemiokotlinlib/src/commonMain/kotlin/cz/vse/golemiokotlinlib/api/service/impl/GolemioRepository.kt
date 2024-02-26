package cz.vse.golemiokotlinlib.api.service.impl

import cz.vse.golemiokotlinlib.api.model.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.api.model.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.api.model.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.api.model.featurescollection.Feature
import cz.vse.golemiokotlinlib.api.model.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.api.model.featurescollection.Garden
import cz.vse.golemiokotlinlib.api.model.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.api.model.featurescollection.MunicipalAuthority
import cz.vse.golemiokotlinlib.api.model.featurescollection.MunicipalLibrary
import cz.vse.golemiokotlinlib.api.model.featurescollection.MunicipalPoliceStation
import cz.vse.golemiokotlinlib.api.model.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.api.model.featurescollection.ParkingV2
import cz.vse.golemiokotlinlib.api.model.featurescollection.Playground
import cz.vse.golemiokotlinlib.api.model.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.api.model.responsedata.PlaygroundProperties
import cz.vse.golemiokotlinlib.api.model.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.api.model.responsedata.BicycleCounterDetection
import cz.vse.golemiokotlinlib.api.model.responsedata.BicycleCounterTemperature
import cz.vse.golemiokotlinlib.api.model.responsedata.MedicalInstitutionTypes
import cz.vse.golemiokotlinlib.api.model.responsedata.MunicipalAuthorityQueue
import cz.vse.golemiokotlinlib.api.model.responsedata.ParkingsV1History
import cz.vse.golemiokotlinlib.api.model.responsedata.ResponseData
import cz.vse.golemiokotlinlib.api.model.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.api.model.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.api.model.responsedata.WasteStationsPicks
import cz.vse.golemiokotlinlib.api.service.IGolemioRepository
import cz.vse.golemiokotlinlib.api.service.MedicalGroup
import cz.vse.golemiokotlinlib.api.service.MunicipalAuthorityType
import cz.vse.golemiokotlinlib.api.service.WasteStationAccessibility
import cz.vse.golemiokotlinlib.api.service.impl.HttpClientFactory.createHttpClient
import cz.vse.golemiokotlinlib.data.ApiException
import cz.vse.golemiokotlinlib.data.RequestParamsBuilder
import cz.vse.golemiokotlinlib.data.RequestType
import io.ktor.client.call.body
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.appendPathSegments

/**
 * Client for handling all types of API requests.
 */
class GolemioRepository(
    endpoint: String = API_URL,
) : IGolemioRepository {

    private var logLevel = LogLevel.BODY
    private val httpClient =
        createHttpClient(
            logLevel
        )
    private val productQueryUrl = with(URLBuilder(endpoint)) {
        build()
    }

    // region air-quality
    override suspend fun getAllAirQualityStations(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation> = getFeatureCollection<AirQualityStation>(
        xAccessToken,
        RequestType.AIR_QUALITY_STATIONS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getAirQualityStationsHistory(
        xAccessToken: String,
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory> {
        return getListResponseData<AirQualityStationHistory>(
            xAccessToken,
            RequestType.AIR_QUALITY_STATIONS,
            "history",
            RequestParamsBuilder.getLimit(limit),
            RequestParamsBuilder.getOffset(offset),
            RequestParamsBuilder.getFrom(from),
            RequestParamsBuilder.getTo(to),
            RequestParamsBuilder.getSensorId(sensorId),
        )
    }

    // endregion
    // region bicycle-counters
    override suspend fun getAllBicycleCounters(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?
    ): List<BicycleCounter> = getFeatureCollection<BicycleCounter>(
        xAccessToken,
        RequestType.BICYCLE_COUNTERS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
    )

    override suspend fun getBicycleCountersDetections(
        xAccessToken: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String
    ): List<BicycleCounterDetection> = getListResponseData(
        xAccessToken,
        RequestType.BICYCLE_COUNTERS,
        "detections",
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getFrom(from),
        RequestParamsBuilder.getTo(to),
        RequestParamsBuilder.getAggregate(aggregate),
        RequestParamsBuilder.getSimpleId(id),
    )

    override suspend fun getBicycleCountersTemperatures(
        xAccessToken: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?
    ): List<BicycleCounterTemperature> = getListResponseData(
        xAccessToken,
        RequestType.BICYCLE_COUNTERS,
        "temperatures",
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getFrom(from),
        RequestParamsBuilder.getTo(to),
        RequestParamsBuilder.getAggregate(aggregate),
        RequestParamsBuilder.getIds(ids),
    )

    // endregion
    // region city-districts
    override suspend fun getAllCityDistricts(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<CityDistrict> = getFeatureCollection(
        xAccessToken,
        RequestType.CITY_DISTRICTS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getCityDistrictById(
        xAccessToken: String,
        id: String
    ): CityDistrict = getFeatureById(xAccessToken, RequestType.CITY_DISTRICTS, id)

    // endregion
    // region gardens
    override suspend fun getAllGardens(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Garden> = getFeatureCollection(
        xAccessToken = xAccessToken,
        RequestType.GARDENS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getGardenById(xAccessToken: String, id: String): Garden =
        getFeatureById(xAccessToken, RequestType.GARDENS, id)

    // endregion
    // region medical-institutions
    override suspend fun getAllMedicalInstitutions(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MedicalInstitution> = getFeatureCollection(
        xAccessToken,
        RequestType.MEDICAL_INSTITUTIONS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getMedicalGroup(group),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince)
    )

    override suspend fun getMedicalInstitutionById(
        xAccessToken: String,
        id: String
    ): MedicalInstitution = getFeatureById(
        xAccessToken,
        RequestType.MEDICAL_INSTITUTIONS,
        id
    )

    override suspend fun getMedicalInstitutionTypes(
        xAccessToken: String
    ) = getObjectResponseData<MedicalInstitutionTypes>(
        xAccessToken,
        RequestType.MEDICAL_INSTITUTIONS,
        "types"
    )

    // endregion
    // region municipal-authorities
    override suspend fun getAllMunicipalAuthorities(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalAuthority> = getFeatureCollection<MunicipalAuthority>(
        xAccessToken,
        RequestType.MUNICIPAL_AUTHORITIES,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getMunicipalAuthorityType(type),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince)
    )

    override suspend fun getMunicipalAuthorityById(
        xAccessToken: String,
        id: String
    ): MunicipalAuthority = getFeatureById<MunicipalAuthority>(
        xAccessToken,
        RequestType.MUNICIPAL_AUTHORITIES,
        id
    )

    override suspend fun getMunicipalAuthoritiesQueues(
        xAccessToken: String,
        id: String
    ): MunicipalAuthorityQueue = getObjectResponseData<MunicipalAuthorityQueue>(
        xAccessToken,
        RequestType.MUNICIPAL_AUTHORITIES,
        id,
        "queues",
    )

    // endregion
    // region municipal-libraries
    override suspend fun getAllMunicipalLibraries(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalLibrary> = getFeatureCollection<MunicipalLibrary>(
        xAccessToken,
        RequestType.MUNICIPAL_LIBRARIES,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getMunicipalLibraryById(
        xAccessToken: String,
        id: String
    ): MunicipalLibrary = getFeatureById(
        xAccessToken,
        RequestType.MUNICIPAL_LIBRARIES,
        id,
    )

    // endregion
    // region municipal-police-stations
    override suspend fun getAllMunicipalPoliceStations(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalPoliceStation> = getFeatureCollection<MunicipalPoliceStation>(
        xAccessToken,
        RequestType.MUNICIPAL_POLICE_STATIONS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getMunicipalPoliceStationById(
        xAccessToken: String,
        id: String
    ): MunicipalPoliceStation = getFeatureById<MunicipalPoliceStation>(
        xAccessToken,
        RequestType.MUNICIPAL_POLICE_STATIONS,
        id
    )

    // endregion
    // region parking-v1
    override suspend fun getAllParkingsV1(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<ParkingV1> = getFeatureCollection<ParkingV1>(
        xAccessToken,
        RequestType.PARKING_V1,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getParkingsV1ById(
        xAccessToken: String, id: String
    ): ParkingV1 = getFeatureById(
        xAccessToken,
        RequestType.PARKING_V1,
        id,
    )

    override suspend fun getParkingsV1History(
        xAccessToken: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String
    ): List<ParkingsV1History> = getListResponseData<ParkingsV1History>(
        xAccessToken,
        RequestType.PARKING_V1,
        "history",
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getFrom(from),
        RequestParamsBuilder.getTo(to),
        RequestParamsBuilder.getSensorId(sensorId),
    )

    //endregion
    // region parking-v2
    override suspend fun getAllParkingsV2(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        source: String?,
        sourceId: String?,
        category: List<String>,
        limit: Int?,
        offset: Int?,
        minutesBefore: Int?,
        updatedSince: String?
    ): List<ParkingV2> {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2ById(xAccessToken: String, id: String): ParkingV2 {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2Detail(xAccessToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2DetailById(xAccessToken: String, id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2Measurements(xAccessToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2Tariffs(xAccessToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2TariffsByTariffId(xAccessToken: String) {
        TODO("Not yet implemented")
    }

    // endregion
    // region playgrounds
    override suspend fun getAllPlaygrounds(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Playground> = getFeatureCollection<Playground>(
        xAccessToken,
        RequestType.PLAYGROUNDS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getPlaygroundById(xAccessToken: String, id: String): Playground =
        getFeatureById(
            xAccessToken,
            RequestType.PLAYGROUNDS,
            id,
        )

    override suspend fun getPlaygroundsProperties(xAccessToken: String): List<PlaygroundProperties> =
        getListResponseData<PlaygroundProperties>(xAccessToken, RequestType.PLAYGROUNDS, "properties")

    // endregion
    // region waste-collections
    override suspend fun getSortedWasteStations(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        accessibility: WasteStationAccessibility?,
        limit: Int?,
        offset: Int?,
        onlyMonitored: Boolean?,
        id: String,
        ksnkoId: String,
    ): List<WasteCollection> = getFeatureCollection<WasteCollection>(
        xAccessToken,
        RequestType.WASTE_COLLECTION,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getWasteStationsAccessibility(accessibility),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getSimpleId(id),
        RequestParamsBuilder.getKsnkoId(ksnkoId),

    )

    override suspend fun getSortedWasteStationsMeasurements(
        xAccessToken: String,
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<WasteStationMeasurements> = getListResponseData<WasteStationMeasurements>(
        xAccessToken,
        RequestType.WASTE_COLLECTION,
        "measurements",
        RequestParamsBuilder.getContainerId(containerId),
        RequestParamsBuilder.getKsnkoId(ksnkoId),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getFrom(from),
        RequestParamsBuilder.getTo(to),
    )

    override suspend fun getSortedWasteStationsPicks(
        xAccessToken: String,
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<WasteStationsPicks> = getListResponseData(
        xAccessToken,
        RequestType.WASTE_COLLECTION,
        "picks",
        RequestParamsBuilder.getContainerId(containerId),
        RequestParamsBuilder.getKsnkoId(ksnkoId),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getFrom(from),
        RequestParamsBuilder.getTo(to),
    )

    override suspend fun getSortedWasteStationsPickDays(
        xAccessToken: String,
        sensoneoCode: String?,
        ksnkoId: String?
    ): List<WasteStationsPickDays> = getListResponseData<WasteStationsPickDays>(
        xAccessToken,
        RequestType.WASTE_COLLECTION,
        "pickdays",
        RequestParamsBuilder.getSensoneoCode(sensoneoCode),
        RequestParamsBuilder.getKsnkoId(ksnkoId),
    )

    // endregion

    /**
     * Returns [FeatureCollection] containing [Feature]s of type [T].
     */
    private suspend inline fun <reified T : Feature> getFeatureCollection(
        xAccessToken: String,
        requestType: RequestType,
        vararg params: Pair<String, String>?,
    ): List<T> {
        val get = httpClient.get(
            url = createRequestUrl(requestType),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", xAccessToken)
                params.forEach {
                    it?.let { it1 -> parameter(it1.first, it.second) }
                }
            })

        handleException(get.status)

        val body = get.body() as FeatureCollection<T>
        return body.features.orEmpty()
    }

    private fun handleException(statusCode: HttpStatusCode) {
        if (statusCode != HttpStatusCode.OK) {
            throw ApiException(statusCode.value, statusCode.description)
        }
    }

    /**
     * Returns list of [ResponseData] of type [T].
     */
    private suspend inline fun <reified T : ResponseData> getListResponseData(
        xAccessToken: String,
        requestType: RequestType,
        additionalPath: String? = null,
        vararg params: Pair<String, String>?,
    ): List<T> {
        val get = httpClient.get(
            url = if (additionalPath != null) createRequestUrl(
                requestType,
                additionalPath
            ) else createRequestUrl(requestType),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", xAccessToken)
                params.forEach {
                    if (it != null) {
                        parameter(it.first, it.second)
                    }
                }
            }
        )
        handleException(get.status)
        return get.body() as List<T>
    }

    /**
     * Returns list of [ResponseData] of type [T].
     */
    private suspend inline fun <reified T : ResponseData> getObjectResponseData(
        xAccessToken: String,
        requestType: RequestType,
        vararg additionalPath: String,
    ): T {
        val get = httpClient.get(
            url = createRequestUrl(requestType, *additionalPath),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", xAccessToken)
            }
        )
        handleException(get.status)
        return get.body() as T
    }

    /**
     * Returns of [Feature] of type [T].
     */
    private suspend inline fun <reified T: Feature> getFeatureById(
        xAccessToken: String,
        requestType: RequestType,
        vararg additionalPath: String,
    ): T {
        val get = httpClient.get(
            url = createRequestUrl(requestType, *additionalPath),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", xAccessToken)
            }
        )

        return get.body() as T
    }

    private fun createRequestUrl(
        requestType: RequestType,
        vararg additionalPath: String,
    ): Url = with(URLBuilder(productQueryUrl)) {
        appendPathSegments(requestType.path)
        additionalPath.forEach {
            appendPathSegments(it)
        }
        build()
    }

    companion object {
        private const val API_URL = "https://api.golemio.cz/v2/"
    }
}
