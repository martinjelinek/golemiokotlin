package cz.vse.golemiokotlinlib.common.network

import cz.vse.golemiokotlinlib.common.utils.RequestParamsUtils
import cz.vse.golemiokotlinlib.common.utils.RequestType
import cz.vse.golemiokotlinlib.common.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.common.entity.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.common.entity.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Feature
import cz.vse.golemiokotlinlib.common.entity.featurescollection.FeatureCollection
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Garden
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MunicipalAuthority
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MunicipalLibrary
import cz.vse.golemiokotlinlib.common.entity.featurescollection.MunicipalPoliceStation
import cz.vse.golemiokotlinlib.common.entity.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Playground
import cz.vse.golemiokotlinlib.common.entity.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.common.entity.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.common.entity.responsedata.BicycleCounterDetection
import cz.vse.golemiokotlinlib.common.entity.responsedata.BicycleCounterTemperature
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalGroup
import cz.vse.golemiokotlinlib.common.entity.responsedata.MedicalInstitutionTypes
import cz.vse.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityQueue
import cz.vse.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityType
import cz.vse.golemiokotlinlib.common.entity.responsedata.ParkingsV1History
import cz.vse.golemiokotlinlib.common.entity.responsedata.PlaygroundProperties
import cz.vse.golemiokotlinlib.common.entity.responsedata.ResponseData
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.common.entity.responsedata.WasteStationsPicks
import cz.vse.golemiokotlinlib.common.service.impl.HttpClientFactory
import io.ktor.client.call.body
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

    private val httpClient = HttpClientFactory.createHttpClient()
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
            RequestParamsUtils.getLimit(limit),
            RequestParamsUtils.getOffset(offset),
            RequestParamsUtils.getFrom(from),
            RequestParamsUtils.getTo(to),
            RequestParamsUtils.getSensorId(sensorId),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
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
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getFrom(from),
        RequestParamsUtils.getTo(to),
        RequestParamsUtils.getAggregate(aggregate),
        RequestParamsUtils.getSimpleId(id),
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
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getFrom(from),
        RequestParamsUtils.getTo(to),
        RequestParamsUtils.getAggregate(aggregate),
        RequestParamsUtils.getIds(ids),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getMedicalGroup(group),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince)
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getMunicipalAuthorityType(type),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince)
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getFrom(from),
        RequestParamsUtils.getTo(to),
        RequestParamsUtils.getSensorId(sensorId),
    )
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getUpdatedSince(updatedSince),
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
        RequestParamsUtils.getLatLng(latlng),
        RequestParamsUtils.getRange(range),
        RequestParamsUtils.getDistricts(districts),
        RequestParamsUtils.getWasteStationsAccessibility(accessibility),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getSimpleId(id),
        RequestParamsUtils.getKsnkoId(ksnkoId),

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
        RequestParamsUtils.getContainerId(containerId),
        RequestParamsUtils.getKsnkoId(ksnkoId),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getFrom(from),
        RequestParamsUtils.getTo(to),
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
        RequestParamsUtils.getContainerId(containerId),
        RequestParamsUtils.getKsnkoId(ksnkoId),
        RequestParamsUtils.getLimit(limit),
        RequestParamsUtils.getOffset(offset),
        RequestParamsUtils.getFrom(from),
        RequestParamsUtils.getTo(to),
    )

    override suspend fun getSortedWasteStationsPickDays(
        sensoneoCode: String?,
        ksnkoId: String?
    ): List<WasteStationsPickDays> = getListResponseData<WasteStationsPickDays>(
        RequestType.WASTE_COLLECTION,
        "pickdays",
        RequestParamsUtils.getSensoneoCode(sensoneoCode),
        RequestParamsUtils.getKsnkoId(ksnkoId),
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
        internal const val API_URL = "https://api.golemio.cz/v2/"
    }
}

class ApiException(responseCode: Int, message: String) : Exception("$responseCode: $message")

