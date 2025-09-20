package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.previewCharacters
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Composable function that displays a Snackbar notification when there's an error loading more characters.
 *
 * @param snackbarHostState The [androidx.compose.material3.SnackbarHostState] to show the Snackbar.
 * @param onRetry Callback invoked when the retry action is clicked.
 */
@Composable
fun LoadErrorNotification(
    snackbarHostState: SnackbarHostState,
    onRetry: () -> Unit,
) {
    val message = stringResource(R.string.error_loading_more_characters)
    val retryMessage = stringResource(R.string.retry)
    LaunchedEffect(snackbarHostState) {
        val result = snackbarHostState.showSnackbar(
            message,
            duration = SnackbarDuration.Indefinite,
            actionLabel = retryMessage,
        )
        when (result) {
            SnackbarResult.Dismissed -> {} // Should not happen with Indefinite duration
            SnackbarResult.ActionPerformed -> onRetry()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadErrorNotificationPreview() {
    // create list of fake data for preview
    val fakeData = previewCharacters
    // create pagingData from a list of fake data
    val pagingData = PagingData.from(fakeData)
    // pass pagingData containing fake data to a MutableStateFlow
    val fakeDataFlow = MutableStateFlow(pagingData)
    // pass flow to composable
    HsInterviewTheme {
        Surface {
            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) { paddingValues ->
                CharacterContent(
                    characters = fakeDataFlow.collectAsLazyPagingItems(),
                    modifier = Modifier.padding(paddingValues)
                )
                LoadErrorNotification(
                    snackbarHostState = snackbarHostState,
                    onRetry = {} // For a preview, the retry action can be empty
                )
            }
        }
    }
}