package ai.revealtech.hsinterview.characters

import ai.revealtech.hsinterview.data.CharacterRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the character list screen.
 *
 * This ViewModel is responsible for providing a [androidx.paging.Pager] of characters
 * to the UI, using the [CharacterRepo].
 *
 * @property characterRepo The repository for accessing character data.
 */
@HiltViewModel
class CharacterViewModel @Inject constructor(
    characterRepo: CharacterRepo,
) : ViewModel() {
    /**
     * A [androidx.paging.Pager] that provides a flow of [androidx.paging.PagingData]
     * for characters. The data is cached in the [viewModelScope].
     */
    val pager = characterRepo
        .getCharacters()
        .cachedIn(viewModelScope)
}
