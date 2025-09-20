package ai.revealtech.hsinterview.domain.model

/**
 * Represents a character in the domain layer.
 * This is a plain data class, independent of data sources or UI.
 *
 * @property id The unique ID of the character.
 * @property name The name of the character.
 * @property status The status of the character (e.g., "Alive", "Dead", "unknown").
 * @property species The species of the character.
 * @property gender The gender of the character (e.g., "Female", "Male", "Genderless", "unknown").
 * @property origin The origin location of the character.
 * @property image The URL of the character's image.
 * @property location The last known location of the character.
 * @property episode A list of episode URLs in which the character appeared.
 */
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val image: String,
    val location: String,
    val episode: List<String>
)
