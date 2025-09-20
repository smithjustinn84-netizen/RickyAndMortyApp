package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.local.AppDatabase
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.network.NetworkDataSource
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig

private const val DEFAULT_PAGE_SIZE = 20
private const val DEFAULT_PREFETCH_DISTANCE = DEFAULT_PAGE_SIZE
private const val DEFAULT_INITIAL_LOAD_SIZE = DEFAULT_PAGE_SIZE * 3

/**
 * Creates a [Pager] for [CharacterEntity] objects.
 *
 * This function configures a [Pager] with a [PagingConfig], a [CharacterRemoteMediator],
 * and a PagingSource factory from the local database.
 *
 * @param appDatabase The [AppDatabase] instance for accessing local data.
 * @param networkDataSource The [NetworkDataSource] for fetching remote data.
 * @param applicationContext The application [Context].
 * @param pageSize The number of items to load per page.
 * @param prefetchDistance The distance from the end of the loaded data at which to prefetch more items.
 * @param initialLoadSize The number of items to load initially.
 * @return A [Pager] for [CharacterEntity] objects.
 */
@OptIn(ExperimentalPagingApi::class)
fun pager(
    appDatabase: AppDatabase,
    networkDataSource: NetworkDataSource,
    applicationContext: Context,
    pageSize: Int = DEFAULT_PAGE_SIZE,
    prefetchDistance: Int = DEFAULT_PREFETCH_DISTANCE,
    initialLoadSize: Int = DEFAULT_INITIAL_LOAD_SIZE
): Pager<Int, CharacterEntity> = Pager(
    config = PagingConfig(
        pageSize = pageSize,
        prefetchDistance = prefetchDistance,
        initialLoadSize = initialLoadSize
    ),
    remoteMediator = CharacterRemoteMediator(
        database = appDatabase,
        networkService = networkDataSource,
        context = applicationContext
    ),
    pagingSourceFactory = {
        appDatabase.characterDao().pagingSource()
    }
)
