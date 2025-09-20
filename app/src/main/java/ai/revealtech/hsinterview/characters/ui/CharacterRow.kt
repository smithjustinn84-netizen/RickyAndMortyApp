package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacter
import ai.revealtech.hsinterview.ui.CharacterImage
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.annotation.ExperimentalCoilApi

/**
 * Composable function that displays a single character row in the list.
 *
 * @param character The [ai.revealtech.hsinterview.model.Character] data for the row.
 * @param onClick Callback invoked when the character row is clicked, providing the character's ID.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterRow(
    character: Character,
    onClick: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.vertical_margin),
                horizontal = dimensionResource(R.dimen.horizontal_margin)
            )
            .clickable { onClick(character.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.card_internal_padding)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CharacterImage(character)
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_space)))
            CharacterSummary(character)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterRowPreview() {
    HsInterviewTheme {
        Surface {
            CharacterRow(character = previewCharacter)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharacterRowDarkPreview() {
    HsInterviewTheme {
        Surface {
            CharacterRow(character = previewCharacter)
        }
    }
}
