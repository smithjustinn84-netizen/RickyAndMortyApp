package ai.revealtech.hsinterview.data.network

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location: Location,
    var image: String,
    var episode: List<String>,
    var url: String,
    var created: String
)

@Serializable
data class Origin(
    var name: String,
    var url: String
)

@Serializable
data class Location(
    var name: String,
    var url: String
)

@Serializable
data class Info(
    var count: Int,
    var pages: Int,
    var next: String? = null,
    var prev: String? = null
)

@Serializable
data class CharactersResponse(
    var info: Info,
    var results: List<CharacterDto>
)

val CharactersResponse.images
    get() = results.map { it.image }