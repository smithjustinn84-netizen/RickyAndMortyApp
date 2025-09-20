package ai.revealtech.hsinterview.model

/**
 * Represents the UI model for a character.
 *
 * @property id The unique ID of the character.
 * @property name The name of the character.
 * @property status The status of the character (e.g., "Alive", "Dead", "unknown").
 * @property species The species of the character.
 * @property gender The gender of the character (e.g., "Female", "Male", "Genderless", "unknown").
 * @property origin The origin location of the character.
 * @property location The last known location of the character.
 * @property episode A list of URLs of episodes in which the character appeared.
 * @property image The URL of the character's image.
 */
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
