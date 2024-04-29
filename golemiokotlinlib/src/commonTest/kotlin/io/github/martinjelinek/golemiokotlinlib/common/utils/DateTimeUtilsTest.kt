package io.github.martinjelinek.golemiokotlinlib.common.utils

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for [DateTimeUtils].
 */
class DateTimeUtilsTest {

    private val dateTimeUtils = DateTimeUtils()

    // TODO failing on iOS
    @Test
    fun testFormatDateTime() {
        val testTimeInMillis = 1558157917000L
        val actual = dateTimeUtils.formatDateTime(testTimeInMillis)
        val expected = "2019-05-18T05:38:37.000Z"

        assertEquals(expected, actual)
    }
}
