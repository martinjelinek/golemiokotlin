package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.common.network.IGolemioApi
import cz.vse.golemiokotlinlib.v2.service.IParkingRepository

internal class ParkingRemoteRepository(
    private val api: IGolemioApi
) : IParkingRepository {

    override suspend fun getAllParkings(
        latlng: Pair<String, String>?,
        range: Int?,
        source: String?,
        sourceId: String?,
        category: List<String>,
        limit: Int?,
        offset: Int?,
        minutesBefore: Int?,
        updatedSince: String?
    ) = api.getAllParkings(
        latlng, range, source, sourceId, category, limit, offset, minutesBefore, updatedSince
    )

    override suspend fun getParkingById(id: String) = api.getParkingById(id)

    override suspend fun getParkingDetail() = api.getParkingDetail()

    override suspend fun getParkingDetailById(id: String?) = api.getParkingDetailById(id)

    override suspend fun getParkingMeasurements() = api.getParkingMeasurements()

    override suspend fun getParkingTariffs() = api.getParkingTariffs()

    override suspend fun getParkingTariffsByTariffId() = api.getParkingTariffsByTariffId()
}
