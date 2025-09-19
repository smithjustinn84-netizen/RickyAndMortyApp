package ai.revealtech.hsinterview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

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

class ListOfStringConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
}