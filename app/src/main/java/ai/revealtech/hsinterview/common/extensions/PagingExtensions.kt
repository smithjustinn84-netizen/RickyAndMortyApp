package ai.revealtech.hsinterview.common.extensions

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Maps the items within a [Flow] of [PagingData] from one type to another.
 *
 * @param transform A lambda function to transform each item.
 * @return A [Flow] of [PagingData] containing the transformed items.
 */
fun <T : Any, R : Any> Flow<PagingData<T>>.mapPagingDataItems(
    transform: (T) -> R
): Flow<PagingData<R>> {
    return this.map { pagingData ->
        pagingData.map(transform)
    }
}
