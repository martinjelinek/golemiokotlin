package cz.vse.golemiokotlinlib.v2.network

import cz.vse.golemiokotlinlib.v2.service.urlutils.RequestParamsBuilder
import cz.vse.golemiokotlinlib.v2.service.urlutils.RequestType
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Feature
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Garden
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MunicipalAuthority
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MunicipalLibrary
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.MunicipalPoliceStation
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.ParkingV2
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.Playground
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.v2.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.v2.entity.responsedata.BicycleCounterDetection
import cz.vse.golemiokotlinlib.v2.entity.responsedata.BicycleCounterTemperature
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalGroup
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MedicalInstitutionTypes
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MunicipalAuthorityQueue
import cz.vse.golemiokotlinlib.v2.entity.responsedata.MunicipalAuthorityType
import cz.vse.golemiokotlinlib.v2.entity.responsedata.ParkingsV1History
import cz.vse.golemiokotlinlib.v2.entity.responsedata.PlaygroundProperties
import cz.vse.golemiokotlinlib.v2.entity.responsedata.ResponseData
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPicks
import cz.vse.golemiokotlinlib.v2.service.impl.HttpClientFactory
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

internal class GolemioApi(private val apiKey: String) : IGolemioApi {

    private val httpClient = HttpClientFactory.createHttpClient(LogLevel.BODY)
    private val productQueryUrl = with(URLBuilder(API_URL)) {
        build()
    }

    // region air-quality
    override suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation> = getFeatureCollection<AirQualityStation>(
        RequestType.AIR_QUALITY_STATIONS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getAirQualityStationsHistory(
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory> {
        return getListResponseData<AirQualityStationHistory>(
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
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?
    ): List<BicycleCounter> = getFeatureCollection<BicycleCounter>(
        RequestType.BICYCLE_COUNTERS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
    )

    override suspend fun getBicycleCountersDetections(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String
    ): List<BicycleCounterDetection> = getListResponseData(
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
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?
    ): List<BicycleCounterTemperature> = getListResponseData(
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
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<CityDistrict> = getFeatureCollection(
        RequestType.CITY_DISTRICTS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getCityDistrictById(
        id: String
    ): CityDistrict = getFeatureById(RequestType.CITY_DISTRICTS, id)

    // endregion
    // region gardens
    override suspend fun getAllGardens(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Garden> = getFeatureCollection(
        RequestType.GARDENS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getGardenById(id: String): Garden =
        getFeatureById(RequestType.GARDENS, id)

    // endregion
    // region medical-institutions
    override suspend fun getAllMedicalInstitutions(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MedicalInstitution> = getFeatureCollection(
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
        id: String
    ): MedicalInstitution = getFeatureById(
        RequestType.MEDICAL_INSTITUTIONS,
        id
    )

    override suspend fun getMedicalInstitutionTypes() = getObjectResponseData<MedicalInstitutionTypes>(
        RequestType.MEDICAL_INSTITUTIONS,
        "types"
    )

    // endregion
    // region municipal-authorities
    override suspend fun getAllMunicipalAuthorities(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalAuthority> = getFeatureCollection<MunicipalAuthority>(
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
        id: String
    ): MunicipalAuthority = getFeatureById<MunicipalAuthority>(
        RequestType.MUNICIPAL_AUTHORITIES,
        id
    )

    override suspend fun getMunicipalAuthoritiesQueues(
        id: String
    ): MunicipalAuthorityQueue = getObjectResponseData<MunicipalAuthorityQueue>(
        RequestType.MUNICIPAL_AUTHORITIES,
        id,
        "queues",
    )

    // endregion
    // region municipal-libraries
    override suspend fun getAllMunicipalLibraries(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalLibrary> = getFeatureCollection<MunicipalLibrary>(
        RequestType.MUNICIPAL_LIBRARIES,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getMunicipalLibraryById(
        id: String
    ): MunicipalLibrary = getFeatureById(
        RequestType.MUNICIPAL_LIBRARIES,
        id,
    )

    // endregion
    // region municipal-police-stations
    override suspend fun getAllMunicipalPoliceStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<MunicipalPoliceStation> = getFeatureCollection<MunicipalPoliceStation>(
        RequestType.MUNICIPAL_POLICE_STATIONS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getMunicipalPoliceStationById(
        id: String
    ): MunicipalPoliceStation = getFeatureById<MunicipalPoliceStation>(
        RequestType.MUNICIPAL_POLICE_STATIONS,
        id
    )

    // endregion
    // region parking-v1
    override suspend fun getAllParkingsV1(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<ParkingV1> = getFeatureCollection<ParkingV1>(
        RequestType.PARKING_V1,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getParkingsV1ById(id: String): ParkingV1 = getFeatureById(
        RequestType.PARKING_V1,
        id,
    )

    override suspend fun getParkingsV1History(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String
    ): List<ParkingsV1History> = getListResponseData<ParkingsV1History>(
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

    override suspend fun getParkingV2ById(id: String): ParkingV2 {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2Detail() {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2DetailById(id: String?) {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2Measurements() {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2Tariffs() {
        TODO("Not yet implemented")
    }

    override suspend fun getParkingV2TariffsByTariffId() {
        TODO("Not yet implemented")
    }

    // endregion
    // region playgrounds
    override suspend fun getAllPlaygrounds(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Playground> = getFeatureCollection<Playground>(
        RequestType.PLAYGROUNDS,
        RequestParamsBuilder.getLatLng(latlng),
        RequestParamsBuilder.getRange(range),
        RequestParamsBuilder.getDistricts(districts),
        RequestParamsBuilder.getLimit(limit),
        RequestParamsBuilder.getOffset(offset),
        RequestParamsBuilder.getUpdatedSince(updatedSince),
    )

    override suspend fun getPlaygroundById(id: String): Playground =
        getFeatureById(
            RequestType.PLAYGROUNDS,
            id,
        )

    override suspend fun getPlaygroundsProperties(): List<PlaygroundProperties> =
        getListResponseData<PlaygroundProperties>(RequestType.PLAYGROUNDS, "properties")

    // endregion
    // region waste-collections
    override suspend fun getSortedWasteStations(
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
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<WasteStationMeasurements> = getListResponseData<WasteStationMeasurements>(
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
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ): List<WasteStationsPicks> = getListResponseData(
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
        sensoneoCode: String?,
        ksnkoId: String?
    ): List<WasteStationsPickDays> = getListResponseData<WasteStationsPickDays>(
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
        requestType: RequestType,
        vararg params: Pair<String, String>?,
    ): List<T> {
        val get = httpClient.get(
            url = createRequestUrl(requestType),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", apiKey)
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
                header("X-Access-Token", apiKey)
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
        requestType: RequestType,
        vararg additionalPath: String,
    ): T {
        val get = httpClient.get(
            url = createRequestUrl(requestType, *additionalPath),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", apiKey)
            }
        )
        handleException(get.status)
        return get.body() as T
    }

    /**
     * Returns of [Feature] of type [T].
     */
    private suspend inline fun <reified T: Feature> getFeatureById(
        requestType: RequestType,
        vararg additionalPath: String,
    ): T {
        val get = httpClient.get(
            url = createRequestUrl(requestType, *additionalPath),
            block = {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("X-Access-Token", apiKey)
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

class ApiException(responseCode: Int, message: String) : Exception("$responseCode: $message")

