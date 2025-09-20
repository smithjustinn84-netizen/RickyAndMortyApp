package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.characterdetail.domain.GetCharacterUseCase
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

/**
 * ViewModel for the character detail screen.
 *
 * This ViewModel is responsible for fetching and providing the details of a specific character.
 * It uses [GetCharacterUseCase] to get the character data and exposes the UI state via [uiState].
 *
 * @property getCharacterUseCase The use case for fetching character details.
 * @param savedStateHandle Handle to saved state, used to retrieve the character ID.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val characterId: Int = savedStateHandle["characterId"]
        ?: throw IllegalArgumentException("character not found in the arguments")

    // Initialize with a default state
    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.Loading)
    /**
     * The current UI state of the detail screen, exposed as a [StateFlow].
     */
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        fetchCharacter()
    }

    /**
     * Fetches the character details using the [getCharacterUseCase] with the [characterId].
     * Updates the [_uiState] with [DetailUiState.Success] on success, or [DetailUiState.Error] on failure.
     * The UI state is initially set to [DetailUiState.Loading].
     */
    fun fetchCharacter() {
        viewModelScope.launch {
            _uiState.update { DetailUiState.Loading } // Set to loading before fetching
            try {
                val character = getCharacterUseCase(characterId)

                _uiState.update {
                    DetailUiState.Success(character)
                }
            } catch (e: Exception) {
                _uiState.update { // Ensure error state is updated on the main thread
                    DetailUiState.Error("Error: ${e.message}")
                }
            }
        }
    }
}
