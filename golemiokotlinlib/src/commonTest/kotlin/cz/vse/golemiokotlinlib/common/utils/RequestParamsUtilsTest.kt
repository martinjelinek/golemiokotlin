package cz.vse.golemiokotlinlib.common.utils

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
    }
}
