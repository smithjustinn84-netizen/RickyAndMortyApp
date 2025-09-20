package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacters
import ai.revealtech.hsinterview.ui.ErrorScreen
import ai.revealtech.hsinterview.ui.LoadingScreen
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.Column
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
 * @param modifier Modifier for this composable.
 * @param viewModel The [CharacterViewModel] used to fetch character data.
 * @param onClick Callback invoked when a character row is clicked, providing the character's ID.
 */
@Composable
fun CharacterScreen(
    modifier: Modifier = Modifier, // Added default modifier
    viewModel: CharacterViewModel = hiltViewModel(),
    onClick: (Int) -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        val lazyPagingItems = viewModel.pager.collectAsLazyPagingItems()

        when {
            lazyPagingItems.loadState.refresh is LoadState.Error -> {
                ErrorScreen(
                    onRetry = { lazyPagingItems.retry() }
                )
            }

            else -> {
                CharacterContent(
                    characters = lazyPagingItems,
                    modifier = modifier.padding(innerPadding),
                    onClick = onClick
                )
            }
        }

        if (lazyPagingItems.loadState.append is LoadState.Error) {
            LoadErrorNotification(
                snackbarHostState,
                onRetry = { lazyPagingItems.retry() }
            )
        }

        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            LoadingScreen()
        }
    }
}

/**
 * Composable function that displays the list of characters.
 *
 * @param characters The [LazyPagingItems] containing the character data.
 * @param modifier Modifier for this composable.
 * @param onClick Callback invoked when a character row is clicked, providing the character's ID.
 */
@Composable
fun CharacterContent(
    characters: LazyPagingItems<Character>,
    modifier: Modifier = Modifier, // Added default modifier
    onClick: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        LazyColumn {
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
    // create list of fake data for preview
    val fakeData = previewCharacters
    // create pagingData from a list of fake data
    val pagingData = PagingData.from(fakeData)
    // pass pagingData containing fake data to a MutableStateFlow
    val fakeDataFlow = MutableStateFlow(pagingData)
    // pass flow to composable
    HsInterviewTheme {
        Surface {
            CharacterContent(
                characters = fakeDataFlow.collectAsLazyPagingItems(),
                modifier = Modifier
            )
        }
    }
}