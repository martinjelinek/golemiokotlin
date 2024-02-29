package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.common.entity.featurescollection.MunicipalPoliceStation

// TODO
interface IMunicipalPoliceStationsRepository {

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
}
