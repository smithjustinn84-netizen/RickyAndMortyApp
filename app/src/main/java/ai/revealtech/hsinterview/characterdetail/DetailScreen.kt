package ai.revealtech.hsinterview.characterdetail

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.CharacterUi
import ai.revealtech.hsinterview.model.exampleCharacterUis
import ai.revealtech.hsinterview.ui.ErrorScreen
import ai.revealtech.hsinterview.ui.LoadingScreen
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.LocalAsyncImagePreviewHandler

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
                ErrorScreen(error = state.message)
            }

            DetailUiState.Loading -> {
                LoadingScreen()
            }
        }
    }
}

@Composable
fun DetailContent(
    character: CharacterUi,
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
        CharacterDetails(character)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
private fun CharacterDetails(
    character: CharacterUi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.vertical_margin),
                horizontal = dimensionResource(R.dimen.horizontal_margin)
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.list_item_padding)),
            horizontalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                AsyncImage(
                    model = character.image,
                    contentDescription = stringResource(R.string.image_description, character),
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.small_space)))
            Details(
                character = character,
                modifier = Modifier.fillMaxWidth()
            )
        }
        MoreDetails(
            characterUi = character,
            modifier = Modifier.padding(dimensionResource(R.dimen.small_space))
        )
    }
}

@Composable
private fun Details(
    character: CharacterUi,
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
    }
}

@Composable
fun MoreDetails(
    characterUi: CharacterUi,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        DetailText(
            label = stringResource(R.string.location),
            text = characterUi.location
        )
        DetailText(
            label = stringResource(R.string.origin),
            text = characterUi.origin
        )
        DetailText(
            label = stringResource(R.string.episodes),
            text = characterUi.episode.size.toString()
        )
    }
}

@Composable
fun DetailText(
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
            .padding(horizontal = dimensionResource(R.dimen.horizontal_margin)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back button",
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
            DetailContent(character = exampleCharacterUis[0], onBackClick = {})
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
            CharacterDetails(character = exampleCharacterUis[0])
        }
    }
}