package ai.revealtech.hsinterview.data.network

import ai.revealtech.hsinterview.model.CharacterResponse
import ai.revealtech.hsinterview.model.CharactersResponse
import ai.revealtech.hsinterview.model.Info
import ai.revealtech.hsinterview.model.Location
import ai.revealtech.hsinterview.model.Origin
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterNetworkDataSource @Inject constructor() : NetworkDataSource {

    private var characters = exampleCharactersResponse()

    override suspend fun loadCharacters(page: Int): CharactersResponse {
        delay(SERVICE_LATENCY_IN_MILLIS)
        return characters
    }

    override suspend fun loadCharacter(id: Int): CharacterResponse? {
        delay(SERVICE_LATENCY_IN_MILLIS)
        return characters.results.find { it.id == id }
    }
}

private const val SERVICE_LATENCY_IN_MILLIS = 2000L

val exampleCharacters = arrayListOf(
    CharacterResponse(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        origin = Origin("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
        location = Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    ),
    CharacterResponse(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male",
        origin = Origin("unknown"),
        location = Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
    ),
    CharacterResponse(
        id = 3,
        name = "Summer Smith",
        status = "Alive",
        species = "Human",
        gender = "Female",
        origin = Origin(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        location = Location(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
    ),
    CharacterResponse(
        id = 4,
        name = "Beth Smith",
        status = "Alive",
        species = "Human",
        gender = "Female",
        origin = Origin(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        location = Location(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
    ),
    CharacterResponse(
        id = 5,
        name = "Jerry Smith",
        status = "Alive",
        species = "Human",
        gender = "Male",
        origin = Origin(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        location = Location(
            "Earth (Replacement Dimension)",
            "https://rickandmortyapi.com/api/location/20"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
    )
)

fun exampleCharactersResponse() = CharactersResponse(
    info = Info(
        count = 671,
        pages = 34,
        next = "https://rickandmortyapi.com/api/character?page=2",
        prev = null
    ),
    results = exampleCharacters
)