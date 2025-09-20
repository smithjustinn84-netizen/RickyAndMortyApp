package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacters
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays the character's name, status, and species.
 *
 * @param character The [ai.revealtech.hsinterview.model.Character] data for the summary.
 * @param modifier Modifier for this composable.
 */
@Composable
fun CharacterSummary(
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineMedium,
        )
        Row(verticalAlignment = Alignment.CenterVertically) { // Added vertical alignment
            StatusIcon(character.status)
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_space)))
            Text(text = "${character.status} - ${character.species}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterSummaryPreview() {
    CharacterSummary(
        character = previewCharacters[0]
    )
}