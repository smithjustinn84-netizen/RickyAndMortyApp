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

interface CharacterRepo {
    fun getCharacters(): Flow<PagingData<CharacterUi>>
    suspend fun getCharacter(id: Int): CharacterUi
}

@Singleton
class CharacterRepoImpl @Inject constructor(
    private val pager: Pager<Int, CharacterEntity>,
    private val dao: CharacterDao
) : CharacterRepo {

    override fun getCharacters(): Flow<PagingData<CharacterUi>> {
        return pager.flow.map { pagingData -> pagingData.map { it.toUi() } }
    }

    override suspend fun getCharacter(id: Int): CharacterUi {
        return dao.getCharacter(id).toUi()
    }
}