package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.model.previewCharacter
import ai.revealtech.hsinterview.ui.ErrorScreen
import ai.revealtech.hsinterview.ui.LoadingScreen
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.LocalAsyncImagePreviewHandler

/**
 * Composable function that displays the character detail screen.
 * It observes the [DetailUiState] from the [DetailViewModel] and shows
 * loading, error, or success states accordingly.
 *
 * @param modifier Modifier for this composable.
 * @param viewModel The [DetailViewModel] used to fetch character details.
 * @param onBackClick Callback invoked when the back button is clicked.
 */
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.safeDrawing // Explicitly set contentWindowInsets
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        DetailScreenLayout(
            uiState = uiState,
            paddingValues = innerPadding,
            onBackClick = onBackClick
        )
    }
}

/**
 * Layout composable for the DetailScreen.
 * It takes the [DetailUiState] and [PaddingValues] to render the appropriate UI.
 *
 * @param uiState The current UI state to display.
 * @param onBackClick Callback for back navigation.
 * @param paddingValues Padding to apply to the content, typically from a Scaffold.
 */
@Composable
fun DetailScreenLayout(
    uiState: DetailUiState,
    onBackClick: () -> Unit,
    paddingValues: PaddingValues = PaddingValues()
) {
    when (val state = uiState) {
        is DetailUiState.Success -> {
            DetailContent(
                character = state.character,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                onBackClick = onBackClick
            )
        }

        is DetailUiState.Error -> {
            ErrorScreen(
                message = state.message,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
        }

        DetailUiState.Loading -> {
            LoadingScreen(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize())
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(name = "Detail Screen Success", showBackground = true)
@Composable
fun DetailScreenSuccessPreview() {
    HsInterviewTheme {
        // Provide the Coil image handler for previews.
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface {
                DetailScreenLayout(
                    uiState = DetailUiState.Success(character = previewCharacter),
                    onBackClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(name = "Detail Screen Success Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenSuccessPreviewDark() {
    HsInterviewTheme {
        // Provide the Coil image handler for previews.
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface {
                DetailScreenLayout(
                    uiState = DetailUiState.Success(character = previewCharacter),
                    onBackClick = {}
                )
            }
        }
    }
}

@Preview(name = "Detail Screen Loading", showBackground = true)
@Composable
fun DetailScreenLoadingPreview() {
    HsInterviewTheme {
        Surface {
            DetailScreenLayout(
                uiState = DetailUiState.Loading,
                onBackClick = {}
            )
        }
    }
}

@Preview(name = "Detail Screen Loading Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenLoadingPreviewDark() {
    HsInterviewTheme {
        Surface {
            DetailScreenLayout(
                uiState = DetailUiState.Loading,
                onBackClick = {}
            )
        }
    }
}

@Preview(name = "Detail Screen Error", showBackground = true)
@Composable
fun DetailScreenErrorPreview() {
    HsInterviewTheme {
        Surface {
            DetailScreenLayout(
                uiState = DetailUiState.Error(message = "Preview Error: Could not load details."),
                onBackClick = {}
            )
        }
    }
}

@Preview(name = "Detail Screen Error Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenErrorPreviewDark() {
    HsInterviewTheme {
        Surface {
            DetailScreenLayout(
                uiState = DetailUiState.Error(message = "Preview Error: Could not load details."),
                onBackClick = {}
            )
        }
    }
}
