package ai.revealtech.hsinterview.characterdetail.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable function for the header of the detail screen.
 * It displays the character's name and a back button, with elevation.
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
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.small_space)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = characterName,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurface
            )
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
