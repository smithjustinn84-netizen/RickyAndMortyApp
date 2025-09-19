package ai.revealtech.hsinterview.data.network

interface NetworkDataSource {
    suspend fun loadCharacters(page: Int): CharactersResponse
}

