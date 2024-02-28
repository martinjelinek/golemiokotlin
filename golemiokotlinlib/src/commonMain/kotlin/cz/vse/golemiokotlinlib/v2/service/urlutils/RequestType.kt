package cz.vse.golemiokotlinlib.v2.service.urlutils

/**
 * Used for building the request URL.
 */
internal enum class RequestType(val path: String) {
    AIR_QUALITY_STATIONS("airqualitystations"),
    BICYCLE_COUNTERS("bicyclecounters"),
    // todo adjust the original one
    BICYCLE_COUNTERS_DETECTIONS("bicyclecounters/detections"),
    // todo adjust the original one
    BICYCLE_COUNTERS_TEMPERATURES("bicyclecounters/temperatures"),
    CITY_DISTRICTS("citydistricts"),
    GARDENS("gardens"),
    MEDICAL_INSTITUTIONS("medicalinstitutions"),
    MUNICIPAL_AUTHORITIES("municipalauthorities"),
    MUNICIPAL_LIBRARIES("municipallibraries"),
    MUNICIPAL_POLICE_STATIONS("municipalpolicestations"),
    PARKING_V1("parkings"),
    PARKING_V2("parking"),
    PLAYGROUNDS("playgrounds"),
    WASTE_COLLECTION("sortedwastestations"),
    WASTE_YARDS("wastecollectionyards")
}
