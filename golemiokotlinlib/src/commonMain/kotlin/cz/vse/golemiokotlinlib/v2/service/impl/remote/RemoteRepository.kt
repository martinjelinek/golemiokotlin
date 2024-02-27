package cz.vse.golemiokotlinlib.v2.service.impl.remote

import cz.vse.golemiokotlinlib.v2.entity.featurescollection.WasteCollection
import cz.vse.golemiokotlinlib.v2.network.GolemioApi
import cz.vse.golemiokotlinlib.v2.service.Repository
import cz.vse.golemiokotlinlib.v2.service.MedicalGroup
import cz.vse.golemiokotlinlib.v2.service.MunicipalAuthorityType
import cz.vse.golemiokotlinlib.v2.service.WasteStationAccessibility
import cz.vse.golemiokotlinlib.v2.network.IGolemioApi

/**
 * Client for handling all types of API requests.
 */
internal class RemoteRepository(
    private val apiKey: String,
    private val api: IGolemioApi = GolemioApi(apiKey)
) : Repository {

    // endregion
    // region bicycle-counters
    override suspend fun getAllBicycleCounters(
        latlng: Pair<String, String>?, range: Int?, limit: Int?, offset: Int?
    ) = api.getAllBicycleCounters(latlng, range, limit, offset)

    override suspend fun getBicycleCountersDetections(
        limit: Int?, offset: Int?, from: String?, to: String?, aggregate: Boolean?, id: String
    ) = api.getBicycleCountersDetections(limit, offset, from, to, aggregate, id)

    override suspend fun getBicycleCountersTemperatures(
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?,
        aggregate: Boolean?,
        ids: List<String>?
    ) = api.getBicycleCountersTemperatures(limit, offset, from, to, aggregate, ids)

    // endregion
    // region city-districts
    override suspend fun getAllCityDistricts(
        latlng: Pair<String, String>?, range: Int?, limit: Int?, offset: Int?, updatedSince: String?
    ) = api.getAllCityDistricts(latlng, range, limit, offset, updatedSince)

    override suspend fun getCityDistrictById(id: String) = api.getCityDistrictById(id)

    // endregion
    // region gardens
    override suspend fun getAllGardens(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllGardens(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getGardenById(id: String) = api.getGardenById(id)

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
    ) = api.getAllMedicalInstitutions(latlng, range, districts, group, limit, offset, updatedSince)

    override suspend fun getMedicalInstitutionById(id: String) = api.getMedicalInstitutionById(id)

    override suspend fun getMedicalInstitutionTypes() = api.getMedicalInstitutionTypes()

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
    ) = api.getAllMunicipalAuthorities(latlng, range, districts, type, limit, offset, updatedSince)

    override suspend fun getMunicipalAuthorityById(id: String) = api.getMunicipalAuthorityById(id)

    override suspend fun getMunicipalAuthoritiesQueues(id: String) =
        api.getMunicipalAuthoritiesQueues(id)

    // endregion
    // region municipal-libraries
    override suspend fun getAllMunicipalLibraries(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllMunicipalLibraries(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getMunicipalLibraryById(id: String) = api.getMunicipalLibraryById(id)

    // endregion
    // region municipal-police-stations
    override suspend fun getAllMunicipalPoliceStations(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllMunicipalPoliceStations(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getMunicipalPoliceStationById(id: String) =
        api.getMunicipalPoliceStationById(id)

    // endregion
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
    ) = api.getAllParkingsV2(
        latlng, range, source, sourceId, category, limit, offset, minutesBefore, updatedSince
    )

    override suspend fun getParkingV2ById(id: String) = api.getParkingV2ById(id)

    override suspend fun getParkingV2Detail() = api.getParkingV2Detail()

    override suspend fun getParkingV2DetailById(id: String?) = api.getParkingV2DetailById(id)

    override suspend fun getParkingV2Measurements() = api.getParkingV2Measurements()

    override suspend fun getParkingV2Tariffs() = api.getParkingV2Tariffs()

    override suspend fun getParkingV2TariffsByTariffId() = api.getParkingV2TariffsByTariffId()

    // endregion
    // region playgrounds
    override suspend fun getAllPlaygrounds(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ) = api.getAllPlaygrounds(latlng, range, districts, limit, offset, updatedSince)

    override suspend fun getPlaygroundById(id: String) = api.getPlaygroundById(id)

    override suspend fun getPlaygroundsProperties() = api.getPlaygroundsProperties()

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
    ): List<WasteCollection> = api.getSortedWasteStations(
        latlng, range, districts, accessibility, limit, offset, onlyMonitored, id, ksnkoId
    )

    override suspend fun getSortedWasteStationsMeasurements(
        containerId: String, ksnkoId: String?, limit: Int?, offset: Int?, from: String?, to: String?
    ) = api.getSortedWasteStationsMeasurements(containerId, ksnkoId, limit, offset, from, to)

    override suspend fun getSortedWasteStationsPicks(
        containerId: String,
        ksnkoId: String?,
        limit: Int?,
        offset: Int?,
        from: String?,
        to: String?
    ) = api.getSortedWasteStationsPicks(containerId, ksnkoId, limit, offset, from, to)

    override suspend fun getSortedWasteStationsPickDays(sensoneoCode: String?, ksnkoId: String?) =
        api.getSortedWasteStationsPickDays(sensoneoCode, ksnkoId)

    // endregion
}
