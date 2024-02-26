package cz.vse.golemiokotlinlib.data

import cz.vse.golemiokotlinlib.api.service.MedicalGroup
import cz.vse.golemiokotlinlib.api.service.MunicipalAuthorityType
import cz.vse.golemiokotlinlib.api.service.WasteStationAccessibility


/**
 * Helper object for building various params used in requests bodies.
 * TODO builder is a pattern; use it
 * TODO poreferovat o predchozim stavu a proc jsem se rozhodl to udelat takhle
 */
object RequestParamsBuilder {

    fun getLatLng(latlng: Pair<String, String>?): Pair<String, String>? {
        return if (latlng != null) {
            val latlngFormatted = "${latlng.first},${latlng.second}"
            Pair("latlng", latlngFormatted)
        } else {
            null
        }
    }

    fun getRange(range: Int?) = if (range != null) Pair("range", range.toString()) else null

    // TODO find out whether there is a list of all possible districts and implement it as datatype if there is
    fun getDistricts(districts: List<String>?): Pair<String, String>? = getListParam("districts", districts)

    fun getLimit(limit: Int?): Pair<String, String>? = getSimpleParam("limit", limit?.toString())

    fun getOffset(offset: Int?): Pair<String, String>? = getSimpleParam("offset", offset?.toString())

    // TODO implement user method that builds updatedSince string from java calendar instance
    fun getUpdatedSince(updatedSince: String?): Pair<String, String>? = if (updatedSince != null) Pair("updatedSince", updatedSince.toString()) else null

    // TODO implement user method that builds updatedSince string from java calendar instance
    fun getFrom(from: String?): Pair<String, String>? = getSimpleParam("from", from)

    // TODO implement user method that builds updatedSince string from java calendar instance
    fun getTo(to: String?): Pair<String, String>? = getSimpleParam("to", to)

    fun getSensorId(sensorId: String?): Pair<String, String>? = getSimpleParam("sensorId", sensorId)

    fun getAggregate(aggregate: Boolean?) = getSimpleParam(aggregate.toString(), "aggregate")

    fun getSimpleId(id: String?) = getSimpleParam("id", id)

    fun getIds(ids: List<String>?): Pair<String, String>? = getListParam("id", ids)

    fun getMedicalGroup(group: MedicalGroup?) = group?.let { getSimpleParam("group", group.group) }

    fun getMunicipalAuthorityType(type: MunicipalAuthorityType?) = type?.let { getSimpleParam("type", type.type) }

    fun getContainerId(containerId: String) = getSimpleParam("containerId", containerId)

    fun getKsnkoId(containerId: String?) = getSimpleParam("ksnkoId", containerId)

    fun getWasteStationsAccessibility(accessibility: WasteStationAccessibility?): Pair<String, String>? {
        return if (accessibility != null) {
            Pair("accessibility", accessibility.accessibility.toString())
        } else null
    }

    fun getSensoneoCode(sensoneoCode: String?) = getSimpleParam("sensoneoCode", sensoneoCode)


    private fun getSimpleParam(paramName: String, value: String?): Pair<String, String>? = if (value != null) Pair(paramName, value) else null

    private fun getListParam(paramName: String, list: List<Any>?): Pair<String, String>? {
        return if (list != null) {
            var listFormatted = ""
            list.forEach {
                listFormatted = listFormatted.plus(it)
                if (it != list.last()) {
                    listFormatted = listFormatted.plus(",")
                }
            }
            Pair(paramName, listFormatted)
        } else {
            null
        }
    }
}
