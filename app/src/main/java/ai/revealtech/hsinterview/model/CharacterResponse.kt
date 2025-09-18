package ai.revealtech.hsinterview.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("status")
    var status: String? = null,
    @SerialName("species")
    var species: String? = null,
    @SerialName("type")
    var type: String? = null,
    @SerialName("gender")
    var gender: String? = null,
    @SerialName("origin")
    var origin: Origin? = Origin(),
    @SerialName("location")
    var location: Location? = Location(),
    @SerialName("image")
    var image: String? = null,
    @SerialName("episode")
    var episode: ArrayList<String> = arrayListOf(),
    @SerialName("url")
    var url: String? = null,
    @SerialName("created")
    var created: String? = null
)

@Serializable
data class Origin(
    @SerialName("name")
    var name: String? = null,
    @SerialName("url")
    var url: String? = null
)

@Serializable
data class Location(
    @SerialName("name")
    var name: String? = null,
    @SerialName("url")
    var url: String? = null
)

@Serializable
data class Info (
    @SerialName("count" )
    var count : Int?    = null,
    @SerialName("pages" )
    var pages : Int?    = null,
    @SerialName("next"  )
    var next  : String? = null,
    @SerialName("prev"  )
    var prev  : String? = null
)

@Serializable
data class CharactersResponse (
    @SerialName("info"    )
    var info    : Info?              = Info(),
    @SerialName("results" )
    var results : ArrayList<CharacterResponse> = arrayListOf()
)