package ai.revealtech.hsinterview.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(users: List<CharacterEntity>)

    @Query("SELECT * FROM character")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM character")
    suspend fun clearAll()

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity
}