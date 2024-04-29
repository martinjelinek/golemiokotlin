package io.github.martinjelinek.golemiokotlinlib.common.utils

expect class DateTimeUtils() {

    /**
     * Takes [dateTime] in millis and converts it to string format for API requests.
     */
    fun formatDateTime(dateTime: Long): String
}