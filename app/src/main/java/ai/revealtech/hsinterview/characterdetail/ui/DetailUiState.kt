package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.model.Character

/**
 * Represents the different states for the character detail screen UI.
 */
sealed class DetailUiState {
    /**
     * Represents a successful state where character data is available.
     * @property character The [Character] object to display.
     */
    data class Success(val character: Character) : DetailUiState()

    /**
     * Represents an error state.
     * @property message The error message to display.
     */
    data class Error(val message: String) : DetailUiState()

    /**
     * Represents a loading state.
     */
    object Loading : DetailUiState()
}
