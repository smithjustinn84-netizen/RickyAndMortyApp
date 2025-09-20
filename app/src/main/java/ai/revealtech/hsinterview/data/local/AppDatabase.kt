package ai.revealtech.hsinterview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database for the application.
 *
 * This database contains the `character` table, represented by the [CharacterEntity] class.
 */
@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Returns the Data Access Object (DAO) for the `character` table.
     *
     * @return An instance of [CharacterDao].
     */
    abstract fun characterDao(): CharacterDao
}
