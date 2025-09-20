package ai.revealtech.hsinterview.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays an error screen.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param message The error message to display. Defaults to "Failed to load".
 * @param onRetry A lambda function to be invoked when the retry button is clicked.
 */
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.failed_to_load),
    onRetry: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error // Changed color
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            imageVector = Icons.Default.CloudOff,
            contentDescription = stringResource(R.string.failed_to_load),
            modifier = Modifier.size(64.dp),
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.error, // Changed color
                blendMode = BlendMode.SrcIn
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * A preview composable function for the [ErrorScreen].
 */
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    HsInterviewTheme {
        Surface {
            ErrorScreen(message = "Failed To Load")
        }
    }
}
