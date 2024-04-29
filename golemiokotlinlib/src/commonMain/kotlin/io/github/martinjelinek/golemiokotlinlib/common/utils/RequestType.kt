package io.github.martinjelinek.golemiokotlinlib.common.utils

/**
 * Used for building the request URL.
 */
internal enum class RequestType(val path: String) {
    AIR_QUALITY_STATIONS("airqualitystations"),
    BICYCLE_COUNTERS("bicyclecounters"),
    CITY_DISTRICTS("citydistricts"),
    GARDENS("gardens"),
    MEDICAL_INSTITUTIONS("medicalinstitutions"),
    MUNICIPAL_AUTHORITIES("municipalauthorities"),
    MUNICIPAL_LIBRARIES("municipallibraries"),
    MUNICIPAL_POLICE_STATIONS("municipalpolicestations"),
    PARKING_V1("parkings"),
    PLAYGROUNDS("playgrounds"),
    WASTE_COLLECTION("sortedwastestations"),
    WASTE_YARDS("wastecollectionyards")
}
