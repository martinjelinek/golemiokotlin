package cz.vse.golemiokotlinlib.v2.service.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * CIO engine supports jvm and kotlin/native.
 */
object HttpClientFactory {
    fun createHttpClient(logLevel: LogLevel): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    allowStructuredMapKeys = true
                    prettyPrint = false
                    useArrayPolymorphism = false
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) = println(message)
            }
            level = logLevel
        }
    }
}
