package cz.vse.golemiokotlinlib.v2.network

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.AirQualityStation
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.BicycleCounter
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.CityDistrict
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
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPicks

/**
 * Manages connection to Golemio API and retrieves data.
 */
interface IGolemioApi {

    /**
     * @return A list of [AirQualityStation]s..
     */
    suspend fun getAllAirQualityStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<AirQualityStation>

    /**
     * @return [AirQualityStationHistory].
     */
    suspend fun getAirQualityStationsHistory(
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
        latlng: Pair<String, String>?,
        range: Int?,
        limit: Int?,
        offset: Int?,
    ): List<BicycleCounter>

    /**
     * @return list of [BicycleCounterDetection]s.
     */
    suspend fun getBicycleCountersDetections(
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
        id: String
    ): CityDistrict

    /**
     * @return A list of [Garden]s.
     */
    suspend fun getAllGardens(
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
        id: String
    ): Garden

    /**
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
        id: String
    ): MedicalInstitution

    /**
     * @return [MedicalInstitutionTypes]
     */
    suspend fun getMedicalInstitutionTypes(): MedicalInstitutionTypes

    /**
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
        id: String
    ): MunicipalAuthority

    /**
     * @param id Identifier of the municipality. Example: skoduv-palac
     *
     * @return [MunicipalAuthorityQueue].
     */
    suspend fun getMunicipalAuthoritiesQueues(id: String): MunicipalAuthorityQueue

    /**
     * @return A list of [MunicipalLibrary]s.
     */
    suspend fun getAllMunicipalLibraries(
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
    suspend fun getMunicipalLibraryById(id: String): MunicipalLibrary

    /**
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
    suspend fun getMunicipalPoliceStationById(id: String): MunicipalPoliceStation

    /**
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
    suspend fun getParkingsV1ById(id: String): ParkingV1

    /**
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up to this datetime
     * @param sensorId limits data measured by sensor with this id (parameter id of the parking)
     *
     * @return list of [ParkingsV1History].
     */
    suspend fun getParkingsV1History(
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
    suspend fun getParkingV2ById(id: String): ParkingV2

    /**
     * todo
     */
    suspend fun getParkingV2Detail()

    /**
     * todo
     */
    suspend fun getParkingV2DetailById(id: String?)

    /**
     * todo
     */
    suspend fun getParkingV2Measurements()

    /**
     * todo
     */
    suspend fun getParkingV2Tariffs()

    /**
     * todo
     */
    suspend fun getParkingV2TariffsByTariffId()

    /**
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
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<Playground>

    /**
     * @param id playground id
     *
     * @return [Playground].
     */
    suspend fun getPlaygroundById(
        id: String
    ): Playground

    /**
     * todo response only 404 not found - find out why
     */
    suspend fun getPlaygroundsProperties(): List<PlaygroundProperties>

    /**
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
     * @param ksnkoId lists only measurements from container with this ID (now is used KSNKO ID as main identifier)
     * @param limit limits number of retrieved items, max and default is 10000
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up until this datetime
     *
     * @return list of [WasteStationMeasurements]
     */
    suspend fun getSortedWasteStationsMeasurements(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<WasteStationMeasurements>

    /**
     * @param ksnkoId lists only measurements from container with this ID (now is used KSNKO ID as main identifier)
     * @param limit limits number of retrieved items, max and default is 10000
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up until this datetime
     *
     * @return list of [WasteStationsPicks]
     */
    suspend fun getSortedWasteStationsPicks(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
    ): List<WasteStationsPicks>

    /**
     * @param ksnkoId lists only measurements from container with this ID (now is used KSNKO ID as main identifier)
     *
     * @return list of [WasteStationsPickDays]
     */
    suspend fun getSortedWasteStationsPickDays(
        sensoneoCode: String?,
        ksnkoId: String?,
    ): List<WasteStationsPickDays>
}
