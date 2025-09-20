package ai.revealtech.hsinterview.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays an error screen.
 * It shows an error message and an optional retry button.
 *
 * @param modifier Modifier for this composable.
 * @param message The error message to display. Defaults to a generic error message.
 * @param onRetry An optional lambda to be invoked when the retry button is clicked. If null, the button is not shown.
 */
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.error_generic),
    onRetry: (() -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "😢",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            if (onRetry != null) {
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onRetry) {
                    Text(text = stringResource(id = R.string.retry))
                }
            }
        }
    }
}

/**
 * A preview composable function for the [ErrorScreen] with a retry button.
 */
@Preview(showBackground = true)
@Composable
fun ErrorPreviewWithRetry() {
    HsInterviewTheme {
        Surface {
            ErrorScreen(onRetry = {})
        }
    }
}

/**
 * A dark mode preview composable function for the [ErrorScreen] with a retry button.
 */
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorPreviewWithRetryDark() {
    HsInterviewTheme {
        Surface {
            ErrorScreen(onRetry = {})
        }
    }
}

/**
 * A preview composable function for the [ErrorScreen] without a retry button.
 */
@Preview(showBackground = true)
@Composable
fun ErrorPreviewWithoutRetry() {
    HsInterviewTheme {
        Surface {
            ErrorScreen()
        }
    }
}

/**
 * A dark mode preview composable function for the [ErrorScreen] without a retry button.
 */
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ErrorPreviewWithoutRetryDark() {
    HsInterviewTheme {
        Surface {
            ErrorScreen()
        }
    }
}
