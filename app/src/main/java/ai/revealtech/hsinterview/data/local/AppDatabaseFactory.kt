package ai.revealtech.hsinterview.data.local

import android.content.Context
import androidx.room.Room

/**
 * Creates and returns an instance of the Room [AppDatabase].
 *
 * @param applicationContext The application context.
 * @return An instance of [AppDatabase].
 */
fun createAppDatabase(applicationContext: Context): AppDatabase {
    return Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    )
        // This is not recommended for normal apps, but the goal of this sample isn't to
        // showcase all of Room.
        .fallbackToDestructiveMigration(false)
        .build()
}
