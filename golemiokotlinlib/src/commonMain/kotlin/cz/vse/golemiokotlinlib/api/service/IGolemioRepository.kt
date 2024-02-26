package cz.vse.golemiokotlinlib.api.service

import cz.vse.golemiokotlinlib.api.model.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.api.model.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.api.model.featurescollection.CityDistrict
import cz.vse.golemiokotlinlib.api.model.featurescollection.Garden
import cz.vse.golemiokotlinlib.api.model.featurescollection.MedicalInstitution
import cz.vse.golemiokotlinlib.api.model.featurescollection.MunicipalAuthority
import cz.vse.golemiokotlinlib.api.model.featurescollection.MunicipalLibrary
import cz.vse.golemiokotlinlib.api.model.featurescollection.MunicipalPoliceStation
import cz.vse.golemiokotlinlib.api.model.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.api.model.featurescollection.ParkingV2
import cz.vse.golemiokotlinlib.api.model.featurescollection.Playground
import cz.vse.golemiokotlinlib.api.model.responsedata.PlaygroundProperties
import cz.vse.golemiokotlinlib.api.model.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.api.model.responsedata.AirQualityStationHistory
import cz.vse.golemiokotlinlib.api.model.responsedata.BicycleCounterDetection
import cz.vse.golemiokotlinlib.api.model.responsedata.BicycleCounterTemperature
import cz.vse.golemiokotlinlib.api.model.responsedata.MedicalInstitutionTypes
import cz.vse.golemiokotlinlib.api.model.responsedata.MunicipalAuthorityQueue
import cz.vse.golemiokotlinlib.api.model.responsedata.ParkingsV1History
import cz.vse.golemiokotlinlib.api.model.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.api.model.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.api.model.responsedata.WasteStationsPicks

/**
 * Client for handling all types of API requests.
 * Designed to match the API architecture.
 * For further information about data structures, please visit the api doc.
 *
 * @see [prague public open api](https://api.golemio.cz/docs/public-openapi/)
 * @author Martin Jelínek (mt.jelinek@gmail.com)
 * todo split into more specific clients
 */
interface IGolemioRepository {
    /**
     * @return A list of [AirQualityStation]s..
     */
    suspend fun getAllAirQualityStations(
        // todo zminit odchyleni latlng
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation>

    /**
     * @return [AirQualityStationHistory].
     * TODO v textu poreferovat ze Golemio v "try it out" nic nevraci
     * TODO edit - vraci, ale musi se zmenit rok na pozdejsi - kam az?
     */
    suspend fun getAirQualityStationsHistory(
        xAccessToken: String,
        sensorId: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<AirQualityStationHistory>

    /**
     * @return A list of [BicycleCounter]s.
     */
    suspend fun getAllBicycleCounters(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
    ): List<BicycleCounter>

    /**
     * @return list of [BicycleCounterDetection]s.
     */
    suspend fun getBicycleCountersDetections(
        xAccessToken: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        id: String,
    ) : List<BicycleCounterDetection>

    /**
     * @return [BicycleCounterTemperature]s.
     */
    suspend fun getBicycleCountersTemperatures(
        xAccessToken: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?,
    ) : List<BicycleCounterTemperature>

    /**
     * @return A list of [CityDistrict]s.
     */
    suspend fun getAllCityDistricts(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<CityDistrict>

    /**
     * Gets [CityDistrict] by it's [id].
     */
    suspend fun getCityDistrictById(
        xAccessToken: String,
        id: String
    ): CityDistrict

    /**
     * @return A list of [Garden]s.
     */
    suspend fun getAllGardens(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Garden>

    /**
     * @return [Garden] by it's [id].
     */
    suspend fun getGardenById(
        xAccessToken: String,
        id: String
    ): Garden

    /**
     * @param xAccessToken needed for accessing the API
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return A list of [MedicalInstitution]s.
     */
    suspend fun getAllMedicalInstitutions(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        group: MedicalGroup?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MedicalInstitution>

    /**
     * @param id Identifier of the medical institution; example: 252671-fakultni-nemocnice-v-motole
     *
     * @return [MedicalInstitution]
     */
    suspend fun getMedicalInstitutionById(
        xAccessToken: String,
        id: String
    ): MedicalInstitution

    /**
     * @return [MedicalInstitutionTypes]
     */
    suspend fun getMedicalInstitutionTypes(
        xAccessToken: String
    ): MedicalInstitutionTypes

    /**
     * @param xAccessToken needed for accessing the API
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param type one of [MunicipalAuthorityType]
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return A list of [MunicipalAuthority].
     */
    suspend fun getAllMunicipalAuthorities(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MunicipalAuthority>

    /**
     * @return [MunicipalAuthority] by it's [id].
     */
    suspend fun getMunicipalAuthorityById(
        xAccessToken: String,
        id: String
    ): MunicipalAuthority

    /**
     * @param id Identifier of the municipality. Example: skoduv-palac
     *
     * @return [MunicipalAuthorityQueue].
     */
    suspend fun getMunicipalAuthoritiesQueues(
        xAccessToken: String,
        id: String
    ): MunicipalAuthorityQueue

    /**
     * @return A list of [MunicipalLibrary]s.
     */
    suspend fun getAllMunicipalLibraries(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MunicipalLibrary>

    /**
     * @return [MunicipalLibrary] by it's [id].
     */
    suspend fun getMunicipalLibraryById(
        xAccessToken: String,
        id: String,
    ): MunicipalLibrary

    /**
     * @param xAccessToken needed for accessing the API.
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return A list of [MunicipalPoliceStation].
     */
    suspend fun getAllMunicipalPoliceStations(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MunicipalPoliceStation>

    /**
     * @return [MunicipalPoliceStation] by it's [id].
     */
    suspend fun getMunicipalPoliceStationById(
        xAccessToken: String,
        id: String
    ): MunicipalPoliceStation

    /**
     * @param xAccessToken needed for accessing the API.
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return A list of [ParkingV1]s.
     */
    suspend fun getAllParkingsV1(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<ParkingV1>

    /**
     * @return [ParkingV1] by it's [id].
     */
    suspend fun getParkingsV1ById(
        xAccessToken: String,
        id: String,
    ): ParkingV1

    /**
     * @param xAccessToken needed for accessing the API
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up to this datetime
     * @param sensorId limits data measured by sensor with this id (parameter id of the parking)
     *
     * @return list of [ParkingsV1History].
     */
    suspend fun getParkingsV1History(
        xAccessToken: String,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String,
    ) : List<ParkingsV1History>

    /**
     * @return A list of [ParkingV2].
     */
    suspend fun getAllParkingsV2(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        source: String?,
        sourceId: String?,
        // TODO implement category
        category: List<String>,
        limit: Int?,
        offset: Int?,
        minutesBefore: Int?,
        updatedSince: String?,
    ): List<ParkingV2>

    /**
     * @return [ParkingV2] by it's [id].
     */
    suspend fun getParkingV2ById(
        xAccessToken: String,
        id: String,
    ): ParkingV2

    /**
     * todo
     */
    suspend fun getParkingV2Detail(
        xAccessToken: String,
    )

    /**
     * todo
     */
    suspend fun getParkingV2DetailById(
        xAccessToken: String,
        id: String?,
    )

    /**
     * todo
     */
    suspend fun getParkingV2Measurements(
        xAccessToken: String,
    )

    /**
     * todo
     */
    suspend fun getParkingV2Tariffs(
        xAccessToken: String,
    )

    /**
     * todo
     */
    suspend fun getParkingV2TariffsByTariffId(
        xAccessToken: String,
    )

    /**
     * @param xAccessToken needed for accessing the API
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param updatedSince filters all results with older updated_at than this parameter; example: 2019-05-18T07:38:37.000Z
     *
     * @return list of [Playground]
     */
    suspend fun getAllPlaygrounds(
        xAccessToken: String,
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<Playground>

    /**
     * @param xAccessToken needed for accessing the API
     * @param id playground id
     *
     * @return [Playground].
     */
    suspend fun getPlaygroundById(
        xAccessToken: String,
        id: String
    ): Playground

    /**
     * todo response only 404 not found - find out why
     * @param xAccessToken needed for accessing the API
     */
    suspend fun getPlaygroundsProperties(
        xAccessToken: String,
    ): List<PlaygroundProperties>

    /**
     * @param xAccessToken needed for accessing the API
     * @param latlng sorting by location (latitude first)
     * @param range filter by distance from latlng in meters (range query), depends on the latlng parameter
     * @param districts filter by Prague city districts (slug) separated by comma; example: praha-4
     * @param accessibility filter by [WasteStationAccessibility]
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param onlyMonitored filter only stations with at least one "smart" container with sensor for measurements and pickups
     * @param id main identifier of the station (now is used KSNKO ID as main identifier)
     * @param ksnkoId KSNKO identifier of the station
     *
     * @return A list of [WasteCollection].
     */
    suspend fun getSortedWasteStations(
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
        ): List<WasteCollection>

    /**
     * @param xAccessToken needed for accessing the API
     * @param ksnkoId lists only measurements from container with this ID (now is used KSNKO ID as main identifier)
     * @param limit limits number of retrieved items, max and default is 10000
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up until this datetime
     *
     * @return list of [WasteStationMeasurements]
     */
    suspend fun getSortedWasteStationsMeasurements(
        xAccessToken: String,
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<WasteStationMeasurements>

    /**
     * @param xAccessToken needed for accessing the API
     * @param ksnkoId lists only measurements from container with this ID (now is used KSNKO ID as main identifier)
     * @param limit limits number of retrieved items, max and default is 10000
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up until this datetime
     *
     * @return list of [WasteStationsPicks]
     */
    suspend fun getSortedWasteStationsPicks(
        xAccessToken: String,
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<WasteStationsPicks>

    /**
     * @param xAccessToken needed for accessing the API
     * @param ksnkoId lists only measurements from container with this ID (now is used KSNKO ID as main identifier)
     *
     * @return list of [WasteStationsPickDays]
     */
    suspend fun getSortedWasteStationsPickDays(
        xAccessToken: String,
        sensoneoCode: String?,
        ksnkoId: String?,
    ): List<WasteStationsPickDays>
}

enum class MedicalGroup(val group: String) {
    PHARMACIES("pharmacies"),
    HEALTH_CARE("health_care"),
}

enum class MunicipalAuthorityType(val type: String) {
    MUNICIPALITY("municipality"),
    CITY_HALL("city_hall"),
}

enum class WasteStationAccessibility(val accessibility: Int) {
    ACCESSIBLE(1),
    HOUSE_RESIDENTS_ONLY(2),
    UNKNOWN(3)
}
