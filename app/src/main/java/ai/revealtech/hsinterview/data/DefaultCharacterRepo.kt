package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.network.NetworkDataSource
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.toCharacter
import ai.revealtech.hsinterview.model.toCharacterPage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCharacterRepo @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : CharacterRepo {

    override suspend fun loadCharacters(page: Int): List<Character>? {
        return networkDataSource.loadCharacters(page)?.toCharacterPage()?.results
    }

    override suspend fun loadCharacter(id: Int): Character? {
        return networkDataSource.loadCharacter(id)?.toCharacter()
    }
}