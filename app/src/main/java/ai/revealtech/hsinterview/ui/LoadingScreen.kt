package ai.revealtech.hsinterview.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays a loading screen.
 * It shows a circular progress indicator and a "Loading..." text.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.loading),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

/**
 * A preview composable function for the [LoadingScreen].
 */
@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    HsInterviewTheme {
        LoadingScreen()
    }
}
