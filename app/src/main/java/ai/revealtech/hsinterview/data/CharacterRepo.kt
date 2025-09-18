package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.model.Character

interface CharacterRepo {

    suspend fun loadCharacters(page: Int): List<Character>?

    suspend fun loadCharacter(id: Int): Character?
}