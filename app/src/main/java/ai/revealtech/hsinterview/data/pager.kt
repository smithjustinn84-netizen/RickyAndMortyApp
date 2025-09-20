package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.local.AppDatabase
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.network.NetworkDataSource
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig

/**
 * Creates a [Pager] for [CharacterEntity] objects.
 *
 * This function configures a [Pager] with a [PagingConfig], a [CharacterRemoteMediator],
 * and a PagingSource factory from the local database.
 *
 * @param appDatabase The [AppDatabase] instance for accessing local data.
 * @param networkDataSource The [NetworkDataSource] for fetching remote data.
 * @param applicationContext The application [Context].
 * @return A [Pager] for [CharacterEntity] objects.
 */
@OptIn(ExperimentalPagingApi::class)
fun pager(
    appDatabase: AppDatabase,
    networkDataSource: NetworkDataSource,
    applicationContext: Context
): Pager<Int, CharacterEntity> = Pager(
    config = PagingConfig(pageSize = 20),
    remoteMediator = CharacterRemoteMediator(
        database = appDatabase,
        networkService = networkDataSource,
        context = applicationContext
    ),
    pagingSourceFactory = {
        appDatabase.characterDao().pagingSource()
    }
)
