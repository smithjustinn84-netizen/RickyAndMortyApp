package ai.revealtech.hsinterview.characters

import ai.revealtech.hsinterview.data.CharacterRepo
import ai.revealtech.hsinterview.model.Character
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CharacterUiState {
    data class Success(val characters: List<Character>) : CharacterUiState
    data class Error(val message: String = "") : CharacterUiState
    object Loading : CharacterUiState
}

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepo: CharacterRepo
) : ViewModel() {
    private val _uiState: MutableStateFlow<CharacterUiState> =
        MutableStateFlow(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                characterRepo.loadCharacters(1)?.let {
                    CharacterUiState.Success(it.results)
                } ?: CharacterUiState.Error(message = "Error loading characters")
            }
        }
    }
}