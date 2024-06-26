package io.github.martinjelinek.golemiokotlinlib.v2.service

import io.github.martinjelinek.golemiokotlinlib.common.entity.featurescollection.MunicipalAuthority
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityQueue
import io.github.martinjelinek.golemiokotlinlib.common.entity.responsedata.MunicipalAuthorityType

internal interface IMunicipalAuthoritiesRepository {

    suspend fun getAllMunicipalAuthorities(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        type: MunicipalAuthorityType?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?,
    ): List<MunicipalAuthority>

    suspend fun getMunicipalAuthorityById(id: String): MunicipalAuthority

    suspend fun getMunicipalAuthoritiesQueues(id: String): MunicipalAuthorityQueue
}
