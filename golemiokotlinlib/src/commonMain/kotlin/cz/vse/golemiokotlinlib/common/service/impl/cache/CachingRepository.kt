package cz.vse.golemiokotlinlib.common.service.impl.cache

import kotlin.jvm.JvmName

/**
 * Open class handling common logic for caching.
 * All caching repos provide non-persistent cache using kotlin collections.
 */
open class CachingRepository {

    /**
     * Looks for the required data to [cache].
     * Fetches new data via [fetchData] if cache not present.
     */
    @JvmName("fetchSimpleDataAndCache")
    suspend fun <T> fetchDataAndCache(
        cache: MutableMap<String, T>,
        vararg params: Any?,
        fetchData: suspend () -> T,
    ): T {
        val cacheKey = buildCacheKey(*params)
        val cachedData = cache[cacheKey]
        if (cachedData != null) {
            return cachedData
        }

        val freshData = fetchData()
        cache[cacheKey] = freshData
        return freshData
    }

    /**
     * Looks for the required data to [cache].
     * Fetches new data via [fetchData] if cache not present.
     */
    @JvmName("fetchListDataAndCache")
    suspend fun <T> fetchDataAndCache(
        cache: MutableMap<String, List<T>>,
        vararg params: Any?,
        fetchData: suspend () -> List<T>,
    ): List<T> {
        val cacheKey = buildCacheKey(*params)
        val cachedData = cache[cacheKey]
        if (cachedData != null) {
            return cachedData
        }

        val freshData = fetchData()
        cache[cacheKey] = freshData
        return freshData
    }

    private fun buildCacheKey(vararg params: Any?) : String {
        val paramsList: List<String> = listOf()
        params.forEach {
            paramsList.plus(it.toString())
        }
        return paramsList.joinToString(separator = "|") { it }
    }
}
