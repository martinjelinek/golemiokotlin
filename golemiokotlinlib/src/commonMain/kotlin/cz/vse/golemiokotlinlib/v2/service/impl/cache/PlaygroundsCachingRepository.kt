package cz.vse.golemiokotlinlib.v2.service.impl.cache

import cz.vse.golemiokotlinlib.common.entity.featurescollection.Playground
import cz.vse.golemiokotlinlib.common.entity.responsedata.PlaygroundProperties
import cz.vse.golemiokotlinlib.common.network.GolemioApi
import cz.vse.golemiokotlinlib.common.service.impl.cache.CachingRepository
import cz.vse.golemiokotlinlib.v2.service.IPlaygroundsRepository
import cz.vse.golemiokotlinlib.v2.service.impl.remote.PlaygroundsRemoteRepository

class PlaygroundsCachingRepository(
    private val remoteRepository: IPlaygroundsRepository
) : IPlaygroundsRepository, CachingRepository() {

    private var playgrounds: MutableMap<String, List<Playground>> =
        mutableMapOf()
    private var playgroundsById: MutableMap<String, Playground> =
        mutableMapOf()
    private var properties: List<PlaygroundProperties> = listOf()

    override suspend fun getAllPlaygrounds(
        latlng: Pair<String, String>?,
        range: Int?,
        districts: List<String>?,
        limit: Int?,
        offset: Int?,
        updatedSince: String?
    ): List<Playground> {
        return fetchDataAndCache(
            playgrounds,
            latlng,
            range,
            districts,
            limit,
            offset,
            updatedSince,
        ) {
            remoteRepository.getAllPlaygrounds(
                latlng,
                range,
                districts,
                limit,
                offset,
                updatedSince
            )
        }
    }

    override suspend fun getPlaygroundById(id: String): Playground {
        return fetchDataAndCache(playgroundsById, id) {
            remoteRepository.getPlaygroundById(id)
        }
    }

    override suspend fun getPlaygroundsProperties() = properties.ifEmpty {
        remoteRepository.getPlaygroundsProperties().also {
            properties = it
        }
    }

    companion object Factory {

        /**
         * Creates [PlaygroundsCachingRepository] over [PlaygroundsRemoteRepository] with [GolemioApi] handling the api calls.
         */
        fun create(apiKey: String) =
            PlaygroundsCachingRepository(PlaygroundsRemoteRepository(GolemioApi(apiKey)))
    }
}
