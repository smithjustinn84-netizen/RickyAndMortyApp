package ai.revealtech.hsinterview.di

import ai.revealtech.hsinterview.data.CharacterRepo
import ai.revealtech.hsinterview.data.CharacterRepoImpl
import ai.revealtech.hsinterview.data.network.CharacterNetworkDataSource
import ai.revealtech.hsinterview.data.network.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing repository dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds [CharacterRepoImpl] to [CharacterRepo] interface.
     *
     * @param repository The [CharacterRepoImpl] instance.
     * @return An instance of [CharacterRepo].
     */
    @Singleton
    @Binds
    abstract fun bindCharacterRepo(repository: CharacterRepoImpl): CharacterRepo
}

/**
 * Hilt module for providing data source dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    /**
     * Binds [CharacterNetworkDataSource] to [NetworkDataSource] interface.
     *
     * @param dataSource The [CharacterNetworkDataSource] instance.
     * @return An instance of [NetworkDataSource].
     */
    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: CharacterNetworkDataSource): NetworkDataSource
}
