package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacter
import ai.revealtech.hsinterview.ui.CharacterImage
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.LocalAsyncImagePreviewHandler

/**
 * Composable function that displays the character's image and textual details.
 * It adapts the layout (Row for landscape, Column for portrait) based on available width.
 *
 * @param character The [ai.revealtech.hsinterview.model.Character] object containing details to display.
 * @param modifier Modifier for this composable.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterDetails(
    character: Character,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val detailsModifier = Modifier
            .padding(dimensionResource(R.dimen.medium_space)) // Added common padding

        when {
            maxWidth > 400.dp -> {
                // Landscape layout: Image on the left (40%), details on the right (60%)
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Top
                ) {
                    CharacterImage(
                        character = character,
                        modifier = Modifier
                            .weight(0.4f)
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )
                    Details(
                        character = character,
                        modifier = detailsModifier
                            .weight(0.6f)
                            .fillMaxHeight()
                    )
                }
            }

            else -> {
                // Portrait layout: Image on top, details below
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CharacterImage(
                        character = character,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f), // Example: Square image, adjust as needed
                        contentScale = ContentScale.Crop
                    )
                    Details(
                        character = character,
                        modifier = detailsModifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun CharacterDetailsPreviewPortrait() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface {
                CharacterDetails(character = previewCharacter, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true, widthDp = 700, heightDp = 300) // Simulate landscape
@Composable
fun CharacterDetailsPreviewLandscape() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface {
                CharacterDetails(character = previewCharacter, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
