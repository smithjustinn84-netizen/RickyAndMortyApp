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

    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.Loading)
    /**
     * The current UI state of the detail screen, exposed as a [StateFlow].
     */
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        // Retrieve characterId from SavedStateHandle, accommodating different argument types (Int or String).
        val idArgument: Any? = savedStateHandle["characterId"]
        val characterId = when (idArgument) {
            is Int -> idArgument
            is String -> idArgument.toIntOrNull()
            else -> null
        } ?: throw IllegalArgumentException("Character ID not found or invalid in arguments")
        
        fetchCharacter(characterId)
    }

    /**
     * Fetches the character details using the [getCharacterUseCase] with the provided [id].
     * Updates the [_uiState] with [DetailUiState.Success] on success, or [DetailUiState.Error] on failure.
     * @param id The ID of the character to fetch.
     */
    private fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            try {
                val character = getCharacterUseCase(id)
                _uiState.update {
                    DetailUiState.Success(character)
                }
            } catch (e: Exception) {
                val errorMessage = e.message?.takeIf { it.isNotBlank() } ?: "An unknown error occurred."
                _uiState.update {
                    DetailUiState.Error(errorMessage)
                }
            }
        }
    }
}
