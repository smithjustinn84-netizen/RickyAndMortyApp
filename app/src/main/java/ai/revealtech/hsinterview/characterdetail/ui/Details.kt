package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.ui.previewHandler
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.LocalAsyncImagePreviewHandler

/**
 * Composable function that displays a list of character attributes (name, status, species, etc.).
 * This section is scrollable.
 *
 * @param character The [ai.revealtech.hsinterview.model.Character] object containing the details.
 * @param modifier Modifier for this composable.
 */
@Composable
fun Details(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.small_space)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_space))
    ) {
        DetailText(
            label = stringResource(R.string.name),
            text = character.name,
            modifier = Modifier.fillMaxWidth()
        )
        DetailText(
            label = stringResource(R.string.status),
            text = character.status,
            modifier = Modifier.fillMaxWidth()
        )
        DetailText(
            label = stringResource(R.string.species),
            text = character.species,
            modifier = Modifier.fillMaxWidth()
        )
        DetailText(
            label = stringResource(R.string.gender),
            text = character.gender,
            modifier = Modifier.fillMaxWidth()
        )
        DetailText(
            label = stringResource(R.string.location),
            text = character.location,
            modifier = Modifier.fillMaxWidth()
        )
        DetailText(
            label = stringResource(R.string.origin),
            text = character.origin,
            modifier = Modifier.fillMaxWidth()
        )
        DetailText(
            label = stringResource(R.string.episodes),
            text = character.episode.size.toString(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Composable function for displaying a labeled piece of text information.
 * The label is aligned to the start and the text to the end.
 *
 * @param label The label for the text information.
 * @param text The actual text information to display.
 * @param modifier Modifier for this composable. Should typically be Modifier.fillMaxWidth().
 */
@Composable
fun DetailText(
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(vertical = dimensionResource(R.dimen.extra_small_space)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = dimensionResource(R.dimen.small_space))
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun DetailTextPreview() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface(Modifier.fillMaxWidth()) {
                DetailText(
                    label = "Status",
                    text = "Alive",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.small_space))
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun DetailTextLongValuePreview() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface(Modifier.fillMaxWidth()) {
                DetailText(
                    label = "Location",
                    text = "Earth (Replacement Dimension) is the current location for this character, it is a very long string to test wrapping.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.small_space))
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(showBackground = true)
@Composable
fun DetailTextLongLabelPreview() {
    HsInterviewTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            Surface(Modifier.fillMaxWidth()) {
                DetailText(
                    label = "Character's Current Location Name",
                    text = "Citadel of Ricks",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.small_space))
                )
            }
        }
    }
}
