package ai.revealtech.hsinterview.di

import ai.revealtech.hsinterview.data.local.AppDatabase
import ai.revealtech.hsinterview.data.local.CharacterDao
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.local.createAppDatabase
import ai.revealtech.hsinterview.data.network.NetworkDataSource
import ai.revealtech.hsinterview.data.network.httpClient
import ai.revealtech.hsinterview.data.pager
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing application-level dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an [HttpClient] instance.
     *
     * @return An [HttpClient] instance.
     */
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return httpClient()
    }

    /**
     * Provides an [AppDatabase] instance.
     *
     * @param applicationContext The application context.
     * @return An [AppDatabase] instance.
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return createAppDatabase(applicationContext)
    }

    /**
     * Provides a [Pager] for [CharacterEntity] instances.
     *
     * @param appDatabase The application database.
     * @param networkDataSource The network data source.
     * @param applicationContext The application context.
     * @return A [Pager] for [CharacterEntity] instances.
     */
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideCharacterPager(
        appDatabase: AppDatabase,
        networkDataSource: NetworkDataSource,
        @ApplicationContext applicationContext: Context
    ): Pager<Int, CharacterEntity> {
        return pager(appDatabase, networkDataSource, applicationContext)
    }

    /**
     * Provides a [CharacterDao] instance.
     *
     * @param appDatabase The application database.
     * @return A [CharacterDao] instance.
     */
    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao {
        return appDatabase.characterDao()
    }
}




