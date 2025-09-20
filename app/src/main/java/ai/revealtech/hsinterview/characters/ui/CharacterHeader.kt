package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays the header of the character list screen.
 */
@Composable
fun CharacterHeader() {
    Row(
        modifier = Modifier.Companion
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.header_padding)),
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LogoImage()
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterHeaderPreview() {
    HsInterviewTheme {
        Surface {
            CharacterHeader()
        }
    }
}