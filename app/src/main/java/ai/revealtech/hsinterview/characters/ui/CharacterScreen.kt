package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacters
import ai.revealtech.hsinterview.ui.ErrorScreen
import ai.revealtech.hsinterview.ui.LoadingScreen
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow


/**
 * Composable function that displays the main character listing screen.
 * It handles loading states (refresh, append errors) and displays either an error screen,
 * a loading screen, or the character content.
 *
 * @param modifier Modifier for this composable, applied to the Scaffold.
 * @param viewModel The [CharacterViewModel] used to fetch character data.
 * @param onClick Callback invoked when a character row is clicked, providing the character\'s ID.
 */
@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
    onClick: (Int) -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems()

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        val refreshState = lazyPagingItems.loadState.refresh

        when (refreshState) {
            is LoadState.Loading -> {
                LoadingScreen(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }

            is LoadState.Error -> {
                ErrorScreen(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    onRetry = { lazyPagingItems.retry() }
                )
            }

            else -> { // NotLoading or Idle (includes LoadState.NotLoading)
                CharacterContent(
                    characters = lazyPagingItems,
                    modifier = Modifier.padding(innerPadding),
                    onClick = onClick
                )
            }
        }

        // Handle append errors with a Snackbar, doesn't need to fill the screen or use innerPadding here
        if (lazyPagingItems.loadState.append is LoadState.Error) {
            LoadErrorNotification(
                snackbarHostState,
                onRetry = { lazyPagingItems.retry() }
            )
        }
    }
}

/**
 * Composable function that displays the list of characters.
 *
 * @param characters The [LazyPagingItems] containing the character data.
 * @param modifier Modifier for this composable. This will include padding from the Scaffold.
 * @param onClick Callback invoked when a character row is clicked, providing the character\'s ID.
 */
@Composable
fun CharacterContent(
    characters: LazyPagingItems<Character>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item { CharacterHeader() }
            items(
                count = characters.itemCount,
                key = { index -> characters.peek(index)?.id ?: index }
            ) { index ->
                characters[index]?.let { character ->
                    CharacterRow(
                        character = character,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterContentPreview() {
    val fakeData = previewCharacters
    val pagingData = PagingData.from(fakeData)
    val fakeDataFlow = MutableStateFlow(pagingData)
    HsInterviewTheme {
        Surface {
            CharacterContent(
                characters = fakeDataFlow.collectAsLazyPagingItems(),
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode") // Added
@Composable
fun CharacterContentDarkPreview() {
    val fakeData = previewCharacters
    val pagingData = PagingData.from(fakeData)
    val fakeDataFlow = MutableStateFlow(pagingData)
    HsInterviewTheme {
        Surface {
            CharacterContent(
                characters = fakeDataFlow.collectAsLazyPagingItems(),
                modifier = Modifier
            )
        }
    }
}

