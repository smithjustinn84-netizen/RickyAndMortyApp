package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.local.AppDatabase
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.mappers.toEntity
import ai.revealtech.hsinterview.data.network.NetworkDataSource
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val database: AppDatabase,
    private val networkService: NetworkDataSource
) : RemoteMediator<Int, CharacterEntity>() {
    private val userDao = database.characterDao()
    private var totalPages = 42
    private var page = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        when (loadType) {
            LoadType.REFRESH -> page
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                when {
                    page < totalPages -> page += 1
                    else -> return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
        }

        val response = networkService.loadCharacters(page)
        totalPages = response.info.pages

        database.withTransaction {
            if (loadType == LoadType.REFRESH) {
                userDao.clearAll()
            }
            userDao.insertAll(response.results.map { it.toEntity() })
        }

        return MediatorResult.Success(
            endOfPaginationReached = response.results.isEmpty()
        )
    }
}
