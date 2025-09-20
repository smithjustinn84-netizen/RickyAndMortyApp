package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacter
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.LocalAsyncImagePreviewHandler

/**
 * Composable function that displays the main content of the detail screen,
 * including the header and character details.
 *
 * @param character The [Character] object to display.
 * @param modifier Modifier for this composable.
 * @param onBackClick Callback invoked when the back button in the header is clicked.
 */
@Composable
fun DetailContent(
    character: Character,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailHeader(
            characterName = character.name,
            onBackClick = onBackClick
        )
        CharacterDetails(
            character = character,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DetailScreenPreviewPortrait() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface {
                DetailContent(character = previewCharacter, onBackClick = {})
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, widthDp = 640, heightDp = 360)
@Composable
fun DetailScreenPreviewLandscape() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface {
                DetailContent(character = previewCharacter, onBackClick = {})
            }
        }
    }
}