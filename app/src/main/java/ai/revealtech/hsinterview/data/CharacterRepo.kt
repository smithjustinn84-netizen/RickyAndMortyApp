package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.local.CharacterDao
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.mappers.toUi
import ai.revealtech.hsinterview.model.CharacterUi
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
     * Returns a [Flow] of [PagingData] for [CharacterUi] objects.
     *
     * @return A [Flow] of [PagingData] containing [CharacterUi] objects.
     */
    fun getCharacters(): Flow<PagingData<CharacterUi>>

    /**
     * Suspended function to get a specific character by its ID.
     *
     * @param id The ID of the character to retrieve.
     * @return The [CharacterUi] object corresponding to the given ID.
     */
    suspend fun getCharacter(id: Int): CharacterUi
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
     * Returns a [Flow] of [PagingData] for [CharacterUi] objects.
     * The data is fetched from the [pager] and mapped to [CharacterUi] objects.
     *
     * @return A [Flow] of [PagingData] containing [CharacterUi] objects.
     */
    override fun getCharacters(): Flow<PagingData<CharacterUi>> {
        return pager.flow.map { pagingData -> pagingData.map { it.toUi() } }
    }

    /**
     * Suspended function to get a specific character by its ID from the local database.
     *
     * @param id The ID of the character to retrieve.
     * @return The [CharacterUi] object corresponding to the given ID.
     */
    override suspend fun getCharacter(id: Int): CharacterUi {
        return dao.getCharacter(id).toUi()
    }
}
