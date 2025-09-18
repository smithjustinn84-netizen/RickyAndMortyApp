package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.CharacterPage

interface CharacterRepo {

    suspend fun loadCharacters(page: Int): CharacterPage?

    suspend fun loadCharacter(id: Int): Character?
}