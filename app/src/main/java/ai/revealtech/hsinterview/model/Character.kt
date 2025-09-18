package ai.revealtech.hsinterview.model


data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val location: String,
    val episode: List<String>,
    val image: String
)

data class CharacterPage(
    val info: PageInfo,
    val results: List<Character>
)

data class PageInfo(
    val count: Int? = null,
    val pages: Int? = null,
    val next: String? = null,
    val prev: String? = null
)

fun CharacterResponse.toCharacter(): Character {
    return Character(
        id = id ?: 0,
        name = name ?: "",
        status = status ?: "",
        species = species ?: "",
        gender = gender ?: "",
        origin = origin?.name ?: "",
        location = location?.name ?: "",
        image = image ?: "",
        episode = episode
    )
}

fun CharactersResponse.toCharacterPage(): CharacterPage {
    return CharacterPage(
        info = info?.toPageInfo() ?: throw IllegalStateException("Info is null"),
        results = results.map { it.toCharacter() }
    )
}

fun Info.toPageInfo(): PageInfo {
    return PageInfo(
        count = count ?: 0,
        pages = pages ?: 0,
        next = next,
        prev = prev
    )
}

val exampleCharacters = listOf(
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        gender = "Male",
        origin = "Earth (C-137)",
        location = "Citadel of Ricks",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        )
    ),
    Character(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        gender = "Male",
        origin = "unknown",
        location = "Citadel of Ricks",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        )
    ),
    Character(
        id = 3,
        name = "Summer Smith",
        status = "Alive",
        species = "Human",
        gender = "Female",
        origin = "Earth (Replacement Dimension)",
        location = "Earth (Replacement Dimension)",
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        )
    ),
    Character(
        id = 4,
        name = "Beth Smith",
        status = "Alive",
        species = "Human",
        gender = "Female",
        origin = "Earth (Replacement Dimension)",
        location = "Earth (Replacement Dimension)",
        image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        )
    ),
    Character(
        id = 5,
        name = "Jerry Smith",
        status = "Alive",
        species = "Human",
        gender = "Male",
        origin = "Earth (Replacement Dimension)",
        location = "Earth (Replacement Dimension)",
        image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2"
        )
    )
)

