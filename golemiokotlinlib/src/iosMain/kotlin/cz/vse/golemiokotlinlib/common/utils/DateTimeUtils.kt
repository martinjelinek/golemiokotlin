package cz.vse.golemiokotlinlib.common.utils

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.timeZoneForSecondsFromGMT

actual class DateTimeUtils {
    actual fun formatDateTime(dateTime: Long): String {
        val date = NSDate(dateTime / 1000.0)
        val formatter = NSDateFormatter().apply {
            dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            timeZone = NSTimeZone.timeZoneForSecondsFromGMT(0)
        }
        return formatter.stringFromDate(date)
    }
}