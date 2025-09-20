package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacters
import ai.revealtech.hsinterview.ui.ErrorScreen
import ai.revealtech.hsinterview.ui.LoadingScreen
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
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
    Scaffold { innerPadding ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when (val state = uiState) {
            is DetailUiState.Success -> {
                DetailContent(
                    character = state.character,
                    modifier = modifier.padding(innerPadding),
                    onBackClick = onBackClick
                )
            }

            is DetailUiState.Error -> {
                ErrorScreen(message = state.message)
            }

            DetailUiState.Loading -> {
                LoadingScreen()
            }
        }
    }
}

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

/**
 * Composable function that displays the character's image and textual details.
 * It adapts the layout (Row for landscape, Column for portrait) based on available width.
 *
 * @param character The [Character] object containing details to display.
 * @param modifier Modifier for this composable.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CharacterDetails(
    character: Character,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
    ) {
        when {
            maxWidth > 400.dp -> {
                // Landscape layout: Image on the left, details on the right
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Start
                ) {
                    CharacterImage(
                        character = character,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Details(
                        character = character,
                        modifier = Modifier
                            .weight(1f)
                            .padding(dimensionResource(R.dimen.small_space))
                    )
                }
            }

            else -> {
                // Portrait layout: Image on top, details below
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CharacterImage(
                        character = character,
                        modifier = Modifier.weight(1f)
                    )
                    Details(
                        character = character,
                        modifier = Modifier
                            .weight(1f)
                            .padding(dimensionResource(R.dimen.small_space))
                    )
                }
            }
        }
    }
}

/**
 * Composable function for displaying the character's image using Coil.
 *
 * @param character The [Character] object containing the image URL and name.
 * @param modifier Modifier for this composable.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CharacterImage(character: Character, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AsyncImage(
            modifier = modifier,
            contentScale = ContentScale.Fit,
            model = character.image,
            contentDescription = stringResource(
                R.string.image_description,
                character.name
            ),
        )
    }
}

/**
 * Composable function that displays a list of character attributes (name, status, species, etc.).
 *
 * @param character The [Character] object containing the details.
 * @param modifier Modifier for this composable.
 */
@Composable
private fun Details(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        DetailText(
            label = stringResource(R.string.name),
            text = character.name
        )
        DetailText(
            label = stringResource(R.string.status),
            text = character.status
        )
        DetailText(
            label = stringResource(R.string.species),
            text = character.species
        )
        DetailText(
            label = stringResource(R.string.gender),
            text = character.gender
        )
        DetailText(
            label = stringResource(R.string.location),
            text = character.location
        )
        DetailText(
            label = stringResource(R.string.origin),
            text = character.origin
        )
        DetailText(
            label = stringResource(R.string.episodes),
            text = character.episode.size.toString()
        )
    }
}

/**
 * Composable function for displaying a labeled piece of text information.
 *
 * @param label The label for the text information.
 * @param text The actual text information to display.
 * @param modifier Modifier for this composable.
 */
@Composable
fun DetailText(
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        DetailText(
            text = text,
        )
    }
}

/**
 * Composable function for displaying a simple text item, used as part of [DetailText].
 *
 * @param text The text to display.
 * @param modifier Modifier for this composable.
 */
@Composable
fun DetailText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

/**
 * Composable function for the header of the detail screen.
 * It displays the character's name and a back button.
 *
 * @param characterName The name of the character to display in the header.
 * @param modifier Modifier for this composable.
 * @param onBackClick Callback invoked when the back button is clicked.
 */
@Composable
fun DetailHeader(
    characterName: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.small_space)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back_button),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = characterName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    HsInterviewTheme {
        Surface {
            DetailContent(character = previewCharacters[0], onBackClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailHeaderPreview() {
    HsInterviewTheme {
        Surface {
            DetailHeader(characterName = "Rick Sanchez", onBackClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterDetailsPreview() {
    HsInterviewTheme {
        Surface {
            CharacterDetails(character = previewCharacters[0])
        }
    }
}
