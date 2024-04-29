package io.github.martinjelinek.golemiokotlinlib.common.utils

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Test class for [RequestParamsUtils].
 */
class RequestParamsUtilsTest {

    @Test
    fun testListParams() {
        val testIntParams = RequestParamsUtils.getListParam(
            "test", listOf(1, 2, 3, 4, 5)
        )
        assertEquals(testIntParams?.first, "test")
        assertEquals(testIntParams?.second, "1,2,3,4,5")

        val testStringParams = RequestParamsUtils.getListParam(
            "test", listOf("1", "2", "3", "4", "5")
        )
        assertEquals(testStringParams?.first, "test")
        assertEquals(testStringParams?.second, "1,2,3,4,5")

        val testNullParam = RequestParamsUtils.getListParam("", null)
        assertEquals(testNullParam, null)
    }

    @Test
    fun testGetLatLng() {
        val testPositive = RequestParamsUtils.getLatLng(Pair("12.1234", "98.765"))
        assertEquals(testPositive?.first, "latlng")
        assertEquals(testPositive?.second, "12.1234,98.765")

        val testNegative = RequestParamsUtils.getLatLng(null)
        assertEquals(testNegative, null)
    }
}
