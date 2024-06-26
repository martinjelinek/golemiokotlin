package io.github.martinjelinek.golemiokotlinlib.common.utils

import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MedicalGroup
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityType
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.WasteStationAccessibility

/**
 * Helper object for building various params used in requests bodies.
 */
object RequestParamsUtils {

    fun getLatLng(latlng: Pair<String, String>?): Pair<String, String>? {
        return if (latlng != null) {
            val latlngFormatted = "${latlng.first},${latlng.second}"
            Pair("latlng", latlngFormatted)
        } else {
            null
        }
    }

    fun getRange(range: Int?) = if (range != null) Pair("range", range.toString()) else null

    fun getDistricts(districts: List<String>?): Pair<String, String>? = getListParam("districts", districts)

    fun getLimit(limit: Int?): Pair<String, String>? = getSimpleParam("limit", limit?.toString())

    fun getOffset(offset: Int?): Pair<String, String>? = getSimpleParam("offset", offset?.toString())

    fun getUpdatedSince(updatedSince: String?): Pair<String, String>? = if (updatedSince != null) Pair("updatedSince", updatedSince.toString()) else null

    fun getFrom(from: String?): Pair<String, String>? = getSimpleParam("from", from)

    fun getTo(to: String?): Pair<String, String>? = getSimpleParam("to", to)

    fun getSensorId(sensorId: String?): Pair<String, String>? = getSimpleParam("sensorId", sensorId)

    fun getAggregate(aggregate: Boolean?) = getSimpleParam(aggregate.toString(), "aggregate")

    fun getSimpleId(id: String?) = getSimpleParam("id", id)

    fun getIds(ids: List<String>?): Pair<String, String>? = getListParam("id", ids)

    fun getMedicalGroup(group: MedicalGroup?) = group?.let { getSimpleParam("group", group.group) }

    fun getMunicipalAuthorityType(type: MunicipalAuthorityType?) = type?.let { getSimpleParam("type", type.type) }

    fun getContainerId(containerId: String) = getSimpleParam("containerId", containerId)

    fun getKsnkoId(ksnkoId: String?) = getSimpleParam("ksnkoId", ksnkoId)

    fun getWasteStationsAccessibility(accessibility: WasteStationAccessibility?): Pair<String, String>? {
        return if (accessibility != null) {
            Pair("accessibility", accessibility.accessibility.toString())
        } else null
    }

    fun getSensoneoCode(sensoneoCode: String?) = getSimpleParam("sensoneoCode", sensoneoCode)

    private fun getSimpleParam(paramName: String, value: String?): Pair<String, String>? = if (value != null) Pair(paramName, value) else null

    fun getListParam(paramName: String, list: List<Any>?): Pair<String, String>? {
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
