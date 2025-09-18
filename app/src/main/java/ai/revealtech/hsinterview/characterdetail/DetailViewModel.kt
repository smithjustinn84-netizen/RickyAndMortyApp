package ai.revealtech.hsinterview.characterdetail

import ai.revealtech.hsinterview.data.CharacterRepo
import ai.revealtech.hsinterview.model.Character
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
    data class Success(val data: Character) : DetailUiState()
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
            val fetchedData = characterRepo.loadCharacter(characterId)

            // Line 45 (example - now safe): Accessing _uiState.value
            _uiState.update {
                if (fetchedData != null) {
                    DetailUiState.Success(fetchedData)
                } else {
                    DetailUiState.Error("Error fetching data")
                }
            }
        }
    }
}
