package ai.revealtech.hsinterview.model


data class CharacterUi(
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
