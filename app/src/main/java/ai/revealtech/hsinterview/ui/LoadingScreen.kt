package ai.revealtech.hsinterview.ui

import ai.revealtech.hsinterview.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * A composable function that displays a loading screen.
 * It shows a circular progress indicator and a "Loading..." text.
 */
@Composable
fun LoadingScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
        Text(stringResource(R.string.loading))
    }
}

/**
 * A preview composable function for the [LoadingScreen].
 */
@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    LoadingScreen()
}
