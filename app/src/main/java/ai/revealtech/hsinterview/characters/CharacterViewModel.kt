package ai.revealtech.hsinterview.characters

import ai.revealtech.hsinterview.data.CharacterPagingSource
import ai.revealtech.hsinterview.data.CharacterRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
) : ViewModel() {
    val pager =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(characterRepo::loadCharacters)
            }
        )
            .flow
            .cachedIn(viewModelScope)
}