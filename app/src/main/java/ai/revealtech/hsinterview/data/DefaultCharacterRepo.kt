package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.network.NetworkDataSource
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.CharacterPage
import ai.revealtech.hsinterview.model.toCharacter
import ai.revealtech.hsinterview.model.toCharacterPage
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCharacterRepo @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : CharacterRepo {

    // A mutex is used to ensure that reads and writes are thread-safe.
    private val accessMutex = Mutex()
    private var cache = mutableMapOf<Int, CharacterPage>()
    private var characterCache = mutableMapOf<Int, Character>()


    override suspend fun loadCharacters(page: Int): CharacterPage? = accessMutex.withLock {
        return when {
            cache.containsKey(page) -> cache[page]
            else -> networkDataSource.loadCharacters(page)?.toCharacterPage()
                ?.also { characterPage ->
                    cache.put(page, characterPage)
                    characterCache.putAll(characterPage.results.associateBy { it.id })
                }
        }
    }

    override suspend fun loadCharacter(id: Int): Character? = accessMutex.withLock {
        return when {
            characterCache.containsKey(id) -> characterCache[id]
            else -> networkDataSource.loadCharacter(id)?.toCharacter()
                ?.also { character -> characterCache[id] = character }
        }
    }
}