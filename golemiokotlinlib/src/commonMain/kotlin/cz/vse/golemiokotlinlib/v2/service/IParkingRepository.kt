package cz.vse.golemiokotlinlib.v2.service

import cz.vse.golemiokotlinlib.common.entity.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.common.entity.featurescollection.Parking

// TODO
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
    suspend fun getAllParkings(
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
    ): List<Parking>

    /**
     * @return [Parking] by it's [id].
     */
    suspend fun getParkingById(id: String): Parking

    /**
     * todo
     */
    suspend fun getParkingDetail()

    /**
     * todo
     */
    suspend fun getParkingDetailById(id: String?)

    /**
     * todo
     */
    suspend fun getParkingMeasurements()

    /**
     * todo
     */
    suspend fun getParkingTariffs()

    /**
     * todo
     */
    suspend fun getParkingTariffsByTariffId()
}