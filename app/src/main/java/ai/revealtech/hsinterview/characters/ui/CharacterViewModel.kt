package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.characters.domain.GetCharactersUseCase
import ai.revealtech.hsinterview.common.extensions.mapPagingDataItems
import ai.revealtech.hsinterview.data.mappers.toUi
import ai.revealtech.hsinterview.model.Character
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ViewModel for the character list screen.
 *
 * This ViewModel is responsible for fetching character data using the [GetCharactersUseCase],
 * mapping it to UI-specific models ([Character]), and providing it as a
 * [Flow] of [PagingData] to the UI.
 *
 * @property getCharactersUseCase The use case for accessing character domain models.
 */
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {
    /**
     * A [Flow] of [PagingData] for [Character] objects, ready for the UI.
     * The data is fetched from the use case, mapped to UI models, and cached in the [viewModelScope].
     */
    val pager: Flow<PagingData<Character>> = getCharactersUseCase()
        .mapPagingDataItems { domainCharacter -> domainCharacter.toUi() }
        .cachedIn(viewModelScope)
}
