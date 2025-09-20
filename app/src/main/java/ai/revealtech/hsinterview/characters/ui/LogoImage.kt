package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays the application logo.
 *
 * @param modifier Modifier for this composable.
 */
@Composable
fun LogoImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(
            id = R.drawable.rick_and_morty_hero,
        ),
        contentDescription = stringResource(R.string.rick_and_morty_logo),
        modifier = modifier
            .requiredHeight(dimensionResource(R.dimen.header_height)),
        contentScale = ContentScale.Fit
    )
}

@Preview(showBackground = true)
@Composable
fun LogoImagePreview() {
    HsInterviewTheme {
        Surface {
            LogoImage()
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LogoImagePreviewDark() {
    HsInterviewTheme {
        Surface {
            LogoImage()
        }
    }
}
