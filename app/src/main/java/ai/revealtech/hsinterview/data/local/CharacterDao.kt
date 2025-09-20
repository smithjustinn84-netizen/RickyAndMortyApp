package ai.revealtech.hsinterview.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object (DAO) for the `character` table.
 */
@Dao
interface CharacterDao {
    /**
     * Inserts a list of [CharacterEntity] into the database.
     * If a character already exists, it will be replaced.
     *
     * @param users The list of characters to insert.
     */
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(users: List<CharacterEntity>)

    /**
     * Returns a [PagingSource] for all characters in the database.
     *
     * @return A [PagingSource] for [CharacterEntity] objects.
     */
    @Query("SELECT * FROM character")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    /**
     * Deletes all characters from the database.
     */
    @Query("DELETE FROM character")
    suspend fun clearAll()

    /**
     * Retrieves a specific character by its ID.
     *
     * @param id The ID of the character to retrieve.
     * @return The [CharacterEntity] with the given ID.
     */
    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity

    /**
     * Returns the total number of characters in the database.
     *
     * @return The count of characters.
     */
    @Query("SELECT COUNT(*) FROM character")
    suspend fun getCharacterCount(): Int

    /**
     * Checks if the character table is empty.
     * This is a default interface method that utilizes [getCharacterCount].
     *
     * @return `true` if the table is empty, `false` otherwise.
     */
    suspend fun isEmpty(): Boolean {
        return getCharacterCount() == 0
    }
}
