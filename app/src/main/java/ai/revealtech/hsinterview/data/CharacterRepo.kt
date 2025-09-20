package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.local.CharacterDao
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.mappers.toDomain
import ai.revealtech.hsinterview.domain.model.Character
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Interface for accessing character data.
 */
interface CharacterRepo {
    /**
     * Returns a [Flow] of [PagingData] for [Character] domain objects.
     *
     * @return A [Flow] of [PagingData] containing [Character] domain objects.
     */
    fun getCharacters(): Flow<PagingData<Character>>

    /**
     * Suspended function to get a specific character by its ID.
     *
     * @param id The ID of the character to retrieve.
     * @return The [Character] domain object corresponding to the given ID.
     */
    suspend fun getCharacter(id: Int): Character
}

/**
 * Implementation of [CharacterRepo] that uses a [Pager] and [CharacterDao].
 *
 * @property pager The [Pager] for [CharacterEntity] objects.
 * @property dao The [CharacterDao] for accessing local character data.
 */
@Singleton
class CharacterRepoImpl @Inject constructor(
    private val pager: Pager<Int, CharacterEntity>,
    private val dao: CharacterDao
) : CharacterRepo {

    /**
     * Returns a [Flow] of [PagingData] for [Character] domain objects.
     * The data is fetched from the [pager] and mapped to [Character] domain objects.
     *
     * @return A [Flow] of [PagingData] containing [Character] domain objects.
     */
    override fun getCharacters(): Flow<PagingData<Character>> {
        // Map from CharacterEntity to domain Character
        return pager.flow.map { pagingData: PagingData<CharacterEntity> ->
            pagingData.map { characterEntity ->
                characterEntity.toDomain()
            }
        }
    }

    /**
     * Suspended function to get a specific character by its ID from the local database.
     *
     * @param id The ID of the character to retrieve.
     * @return The [Character] domain object corresponding to the given ID.
     */
    override suspend fun getCharacter(id: Int): Character {
        // Map from CharacterEntity to domain Character
        return dao.getCharacter(id).toDomain()
    }
}
