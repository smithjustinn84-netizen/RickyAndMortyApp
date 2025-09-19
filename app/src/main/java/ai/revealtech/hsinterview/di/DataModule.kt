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

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCharacterRepo(repository: CharacterRepoImpl): CharacterRepo
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: CharacterNetworkDataSource): NetworkDataSource
}