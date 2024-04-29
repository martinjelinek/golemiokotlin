package io.github.martinjelinek.golemiokotlinlib.common.utils

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

actual class DateTimeUtils {
    actual fun formatDateTime(dateTime: Long): String {
        val instant = Instant.ofEpochMilli(dateTime)
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneOffset.UTC)
            .format(instant)
    }
}