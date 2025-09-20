package ai.revealtech.hsinterview.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler

/**
 * A [AsyncImagePreviewHandler] that displays a light gray placeholder image
 * for Composable previews when using Coil's AsyncImage.
 */
@OptIn(ExperimentalCoilApi::class)
val previewHandler = AsyncImagePreviewHandler {
    ColorImage(
        Color.Companion.LightGray.toArgb(),
        width = 300,
        height = 300,
    )
}
