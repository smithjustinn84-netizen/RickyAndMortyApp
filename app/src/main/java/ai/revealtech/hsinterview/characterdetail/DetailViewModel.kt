package ai.revealtech.hsinterview.characterdetail

import ai.revealtech.hsinterview.data.CharacterRepo
import ai.revealtech.hsinterview.model.CharacterUi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed class DetailUiState {
    data class Success(val character: CharacterUi) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
    object Loading : DetailUiState()
}

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val characterRepo: CharacterRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val characterId: Int = savedStateHandle["characterId"]
        ?: throw IllegalArgumentException("character not found in the arguments")

    // Initialize with a default state
    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        fetchCharacter()
    }

    fun fetchCharacter() {
        viewModelScope.launch {
            try {
                val character = characterRepo.getCharacter(characterId)

                _uiState.update {
                    DetailUiState.Success(character)
                }
            } catch (e: Exception) {
                DetailUiState.Error("Error: ${e.message}")
            }
        }
    }
}
