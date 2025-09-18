package ai.revealtech.hsinterview.data.network

import ai.revealtech.hsinterview.model.CharacterResponse
import ai.revealtech.hsinterview.model.CharactersResponse

interface NetworkDataSource {
    suspend fun loadCharacters(page: Int): CharactersResponse?

    suspend fun loadCharacter(id: Int): CharacterResponse?
}

