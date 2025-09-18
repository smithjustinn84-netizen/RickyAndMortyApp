package ai.revealtech.hsinterview.data

import ai.revealtech.hsinterview.model.Character
import androidx.paging.PagingSource
import androidx.paging.PagingState

class CharacterPagingSource(
    private val loadCharacters: suspend (page: Int) -> List<Character>?
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        // Start refresh at page 1 if undefined.
        val page = params.key ?: 1

        val data = loadCharacters(page) ?: emptyList()
        val nextKey = if (data.isNotEmpty()) page + 1 else null

        return try {
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            LoadResult.Error(e)
        }
    }
}
