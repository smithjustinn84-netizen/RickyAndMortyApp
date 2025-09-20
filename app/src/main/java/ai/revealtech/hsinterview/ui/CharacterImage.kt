package ai.revealtech.hsinterview.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.model.Character
import ai.revealtech.hsinterview.model.previewCharacter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalAsyncImagePreviewHandler
import coil3.compose.rememberAsyncImagePainter

/**
 * Composable function that displays the character's image.
 * It handles loading, success, and error states for the image using Coil.
 *
 * @param character The [ai.revealtech.hsinterview.model.Character] data containing the image URL.
 * @param modifier Modifier for this composable.
 * @param contentScale Optional parameter to control the scaling of the image. Defaults to ContentScale.Fit.
 */
@Composable
@OptIn(ExperimentalCoilApi::class)
fun CharacterImage(
    character: Character,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        // Use the contentScale parameter for the painter
        val painter = rememberAsyncImagePainter(character.image, contentScale = contentScale)
        val state by painter.state.collectAsState()

        when (state) {
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(modifier = modifier)
            }

            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    modifier = modifier,
                    contentScale = contentScale,
                    contentDescription = stringResource(R.string.image_description, character.name),
                )
            }

            is AsyncImagePainter.State.Error -> {
                IconButton(
                    onClick = {
                        painter.restart()
                    },
                    modifier = modifier
                ) {
                    Image(
                        imageVector = Icons.Default.CloudOff,
                        contentDescription = stringResource(R.string.error_loading_image),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CharacterImagePreview() {
    Column {
        CharacterImage(
            character = previewCharacter,
        )
    }
}
