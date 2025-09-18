package ai.revealtech.hsinterview.data.network

import ai.revealtech.hsinterview.model.CharacterResponse
import ai.revealtech.hsinterview.model.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CharacterNetworkDataSource @Inject constructor(
    private val client: HttpClient
) : NetworkDataSource {

    override suspend fun loadCharacters(page: Int): CharactersResponse {
        return client.get("https://rickandmortyapi.com/api/character?page=$page").body()
    }

    override suspend fun loadCharacter(id: Int): CharacterResponse? {
        return client.get("https://rickandmortyapi.com/api/character/$id").body()
    }
}
