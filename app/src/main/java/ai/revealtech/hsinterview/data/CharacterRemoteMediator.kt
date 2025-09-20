package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.data.local.AppDatabase
import ai.revealtech.hsinterview.data.local.CharacterEntity
import ai.revealtech.hsinterview.data.mappers.toEntity
import ai.revealtech.hsinterview.data.network.NetworkDataSource
import ai.revealtech.hsinterview.data.network.images
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import kotlinx.io.IOException
import java.net.UnknownHostException
import kotlin.math.ceil

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val context: Context,
    private val database: AppDatabase,
    private val networkService: NetworkDataSource
) : RemoteMediator<Int, CharacterEntity>() {
    private val userDao = database.characterDao()
    private var totalPages = 42
    private var totalCharacters = 826

    private val pageSize: Int
        get() = ceil(totalCharacters.toDouble() / totalPages).toInt()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,

        ): MediatorResult {
        try {
            val newPage = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val page = state.getCurrentPage()
                    when {
                        page < totalPages -> page + 1
                        else -> return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }
            }

            val response = networkService.loadCharacters(newPage)

            // set total pages and total characters from REST API
            totalPages = response.info.pages
            totalCharacters = response.info.count

            // insert data into database
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.clearAll()
                }
                userDao.insertAll(response.results.map { it.toEntity() })
            }

            preloadImages(response.images, context)

            return MediatorResult.Success(
                endOfPaginationReached = response.results.isEmpty()
            )
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: UnknownHostException) {
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        return if (userDao.isEmpty()) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    private fun PagingState<Int, CharacterEntity>.getCurrentPage(): Int {
        val lastItemId = lastItemOrNull()?.id ?: 1
        return ceil(lastItemId.toDouble() / pageSize).toInt()
    }
}

private fun preloadImages(urls: List<String>, context: Context) {
    urls.forEach { url ->
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()
        SingletonImageLoader.get(context).enqueue(request)
    }
}