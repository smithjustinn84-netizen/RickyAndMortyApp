package ai.revealtech.hsinterview.data.network

/**
 * Defines the contract for accessing character data from a remote network source.
 */
interface NetworkDataSource {
    /**
     * Loads a list of characters for a specific page.
     *
     * @param page The page number to load.
     * @return [ApiCharacters] The raw character data fetched from the API.
     * @throws Exception if there is a network error or the API returns an error.
     */
    suspend fun loadCharacters(page: Int): ApiCharacters
}

