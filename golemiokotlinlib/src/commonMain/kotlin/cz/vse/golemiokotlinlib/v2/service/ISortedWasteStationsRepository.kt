package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationMeasurements
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPickDays
import cz.vse.golemiokotlinlib.v2.entity.responsedata.WasteStationsPicks

// TODO
interface ISortedWasteStationsRepository {

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
