package cz.vse.golemiokotlinlib.data

/**
 * Custom exception for response exceptions.
 */
class ApiException(responseCode: Int, message: String) : Exception("$responseCode: $message")
