package ai.revealtech.hsinterview.characterdetail.domain

import ai.revealtech.hsinterview.data.CharacterRepo
import ai.revealtech.hsinterview.data.mappers.toUi
import ai.revealtech.hsinterview.model.Character
import javax.inject.Inject

/**
 * Use case for fetching a single character.
 *
 * This use case retrieves a character by its ID from the [CharacterRepo]
 * and maps it to a UI model.
 *
 * @property characterRepo The repository for accessing character data.
 */
class GetCharacterUseCase @Inject constructor(
    private val characterRepo: CharacterRepo
) {
    /**
     * Fetches a character by its ID.
     *
     * @param id The ID of the character to fetch.
     * @return The character details as [Character].
     * @throws Exception if the character fetch fails.
     */
    suspend operator fun invoke(id: Int): Character {
        return characterRepo.getCharacter(id).toUi()
    }
}