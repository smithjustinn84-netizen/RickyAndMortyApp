package ai.revealtech.hsinterview.characters

import ai.revealtech.hsinterview.data.CharacterRepo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    characterRepo: CharacterRepo,
) : ViewModel() {
    val pager = characterRepo
        .getCharacters()
        .cachedIn(viewModelScope)
}