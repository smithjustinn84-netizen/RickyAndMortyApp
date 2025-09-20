package ai.revealtech.hsinterview.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import io.ktor.resources.Resource
import javax.inject.Inject

class CharacterNetworkDataSource @Inject constructor(
    private val client: HttpClient
) : NetworkDataSource {

    override suspend fun loadCharacters(page: Int): CharactersResponse {
        return client.get(Character(page)).body()
    }
}

@Resource("/character")
private data class Character(val page: Int)