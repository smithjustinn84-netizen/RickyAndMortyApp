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

/**
 * A [RemoteMediator] for loading characters from the network and saving them to the local database.
 *
 * This class handles pagination, fetching data from the [NetworkDataSource],
 * storing it in the [AppDatabase], and preloading images using Coil.
 *
 * @property context The application [Context].
 * @property database The [AppDatabase] instance.
 * @property networkService The [NetworkDataSource] for fetching character data.
 */
@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val context: Context,
    private val database: AppDatabase,
    private val networkService: NetworkDataSource
) : RemoteMediator<Int, CharacterEntity>() {
    private val userDao = database.characterDao()
    private var totalPages = 42 // Default value, will be updated from API response
    private var totalCharacters = 826 // Default value, will be updated from API response

    private val pageSize: Int
        get() = if (totalPages > 0) ceil(totalCharacters.toDouble() / totalPages).toInt() else 20 // Default page size if totalPages is 0

    /**
     * Loads data from the network based on the [LoadType] and [PagingState].
     *
     * It determines the page to load, fetches data from the [networkService],
     * updates local pagination metadata, inserts the data into the [database],
     * and preloads images.
     *
     * @param loadType The type of load request ([LoadType.REFRESH], [LoadType.PREPEND], [LoadType.APPEND]).
     * @param state The current [PagingState].
     * @return A [MediatorResult] indicating success or failure.
     */
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
                endOfPaginationReached = response.results.isEmpty() || newPage >= totalPages
            )
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: UnknownHostException) {
            return MediatorResult.Error(e)
        }
    }

    /**
     * Initializes the [RemoteMediator].
     *
     * Determines whether to launch an initial refresh based on whether the local database is empty.
     *
     * @return An [InitializeAction] to either launch an initial refresh or skip it.
     */
    override suspend fun initialize(): InitializeAction {
        return if (userDao.isEmpty()) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    /**
     * Calculates the current page based on the last item in the [PagingState].
     *
     * @receiver The current [PagingState].
     * @return The current page number.
     */
    private fun PagingState<Int, CharacterEntity>.getCurrentPage(): Int {
        val lastItemId = lastItemOrNull()?.id ?: 1
        return if (pageSize > 0) ceil(lastItemId.toDouble() / pageSize).toInt() else 1
    }
}

/**
 * Preloads images from the given list of URLs using Coil.
 *
 * @param urls A list of image URLs to preload.
 * @param context The application [Context].
 */
private fun preloadImages(urls: List<String>, context: Context) {
    urls.forEach { url ->
        val request = ImageRequest.Builder(context)
            .data(url)
            .build()
        SingletonImageLoader.get(context).enqueue(request)
    }
}
