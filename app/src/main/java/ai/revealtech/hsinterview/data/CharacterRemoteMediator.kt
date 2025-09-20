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

    companion object {
        private const val UNKNOWN_TOTAL_PAGES = 42
        private const val UNKNOWN_TOTAL_CHARACTERS = 826
        private const val DEFAULT_PAGE_SIZE = 20
        private const val STARTING_PAGE_INDEX = 1
    }

    private val userDao = database.characterDao()
    private var totalPages: Int = UNKNOWN_TOTAL_PAGES
    private var totalCharacters: Int = UNKNOWN_TOTAL_CHARACTERS

    private val pageSize: Int
        get() = if (totalPages > 0 && totalCharacters > 0) {
            maxOf(1, ceil(totalCharacters.toDouble() / totalPages).toInt())
        } else {
            DEFAULT_PAGE_SIZE
        }

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
                LoadType.REFRESH -> {
                    STARTING_PAGE_INDEX
                }

                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val page = state.getCurrentPage()
                    when {
                        totalPages > 0 && page < totalPages -> page + 1
                        totalPages == 0 && page == STARTING_PAGE_INDEX -> STARTING_PAGE_INDEX
                        else -> return MediatorResult.Success(endOfPaginationReached = true)
                    }
                }
            }

            val response = networkService.loadCharacters(newPage)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.clearAll()
                }
                userDao.insertAll(response.results.map { it.toEntity() })
            }

            preloadImages(response.images, context)

            return MediatorResult.Success(
                endOfPaginationReached = response.results.isEmpty() || (totalPages > 0 && newPage >= totalPages)
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
        // If the database is empty, always trigger an initial load.
        return if (userDao.isEmpty()) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            // If the database is not empty, assume it's sufficiently populated.
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
        val lastItem = lastItemOrNull()
        if (lastItem == null) {
            return STARTING_PAGE_INDEX
        }

        return if (pageSize > 0) {
            maxOf(STARTING_PAGE_INDEX, ceil(lastItem.id.toDouble() / pageSize).toInt())
        } else {
            STARTING_PAGE_INDEX
        }
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
