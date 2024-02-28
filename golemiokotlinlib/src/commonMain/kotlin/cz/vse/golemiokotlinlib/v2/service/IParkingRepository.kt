package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.v2.entity.featurescollection.ParkingV2

interface IParkingRepository {

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
}