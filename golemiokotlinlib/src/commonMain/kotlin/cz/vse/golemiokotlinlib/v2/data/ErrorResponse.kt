package cz.vse.golemiokotlinlib.v2.data

/**
 * Custom exception for response exceptions.
 */
class ApiException(responseCode: Int, message: String) : Exception("$responseCode: $message")
