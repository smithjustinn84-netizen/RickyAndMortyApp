package ai.revealtech.hsinterview.characters.domain

import ai.revealtech.hsinterview.data.CharacterRepo
import ai.revealtech.hsinterview.domain.model.Character
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for getting the paginated list of characters.
 * Returns domain model objects.
 */
class GetCharactersUseCase @Inject constructor(
    private val characterRepo: CharacterRepo
) {
    /**
     * Invokes the use case to get a flow of paginated character domain models.
     */
    operator fun invoke(): Flow<PagingData<Character>> {
        return characterRepo.getCharacters()
    }
}
