package io.github.martinjelinek.golemiokotlinlib.common.service.impl

import io.ktor.client.HttpClient

/**
 * CIO engine supports jvm and kotlin/native.
 */
expect object HttpClientFactory {
    fun createHttpClient(): HttpClient
}
