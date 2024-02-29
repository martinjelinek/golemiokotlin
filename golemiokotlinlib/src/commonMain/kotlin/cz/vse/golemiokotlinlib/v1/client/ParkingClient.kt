package cz.vse.golemiokotlinlib.v1.client

import cz.vse.golemiokotlinlib.common.entity.featurescollection.ParkingV1
import cz.vse.golemiokotlinlib.common.entity.responsedata.ParkingsV1History
import cz.vse.golemiokotlinlib.v1.service.IParkingRepository
import cz.vse.golemiokotlinlib.v1.service.impl.cache.ParkingCachingRepository

class ParkingClient(apiKey: String) {

    private val repository: IParkingRepository = ParkingCachingRepository.create(apiKey)

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
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = repository.getAllParkingsV1(latlng, range, districts, limit, offset, updatedSince)

    /**
     * @return [ParkingV1] by it's [id].
     */
    suspend fun getParkingsById(id: String) = repository.getParkingsV1ById(id)

    /**
     * @param limit limits number of retrieved items
     * @param offset number of the first items that are skipped
     * @param from date in ISO8601, limits data measured from this datetime
     * @param to date in ISO8601, limits data measured up to this datetime
     * @param sensorId limits data measured by sensor with this id (parameter id of the parking)
     *
     * @return list of [ParkingsV1History].
     */
    suspend fun getParkingsHistory(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        sensorId: String
    ) = repository.getParkingsV1History(limit, offset, from, to, sensorId)
}
