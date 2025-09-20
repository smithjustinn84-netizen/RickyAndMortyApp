package ai.revealtech.hsinterview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

/**
 * Represents a character entity in the local database.
 *
 * @property id The unique ID of the character (primary key).
 * @property name The name of the character.
 * @property status The status of the character (e.g., "Alive", "Dead", "unknown").
 * @property species The species of the character.
 * @property gender The gender of the character (e.g., "Female", "Male", "Genderless", "unknown").
 * @property origin The origin location of the character.
 * @property image The URL of the character's image.
 * @property location The last known location of the character.
 * @property episode A list of episode URLs in which the character appeared.
 */
@Entity(tableName = "character")
@TypeConverters(ListOfStringConverter::class)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val image: String,
    val location: String,
    val episode: List<String>
)

/**
 * [TypeConverter] for converting a list of strings to a single string and vice-versa.
 * This is used by Room to store the `episode` list in the database.
 */
class ListOfStringConverter {
    /**
     * Converts a comma-separated string to a list of strings.
     *
     * @param value The string to convert.
     * @return A list of strings.
     */
    @TypeConverter
    fun fromString(value: String): List<String> {
        return if (value.isEmpty()) emptyList() else value.split(",")
    }

    /**
     * Converts a list of strings to a single comma-separated string.
     *
     * @param list The list of strings to convert.
     * @return A comma-separated string.
     */
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
}
