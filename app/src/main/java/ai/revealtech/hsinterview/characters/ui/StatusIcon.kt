package ai.revealtech.hsinterview.characters.ui

import ai.revealtech.hsinterview.R
import ai.revealtech.hsinterview.ui.theme.HsInterviewTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error // Added for semantic correctness
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme // Added for theme colors
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier // Added Modifier parameter
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Composable function that displays an icon representing the character's status.
 *
 * @param status The status string of the character (e.g., "Alive", "Dead", "unknown").
 * @param modifier Modifier for this composable.
 */
@Composable
fun StatusIcon(status: String, modifier: Modifier = Modifier) {
    when (status) {
        "Alive" -> {
            Image(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = stringResource(R.string.alive),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
                modifier = modifier
            )
        }

        "Dead" -> {
            Image(
                imageVector = Icons.Filled.Error,
                contentDescription = stringResource(R.string.dead),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error),
                modifier = modifier
            )
        }

        else -> { // "unknown"
            Image(
                imageVector = Icons.Filled.Info,
                contentDescription = stringResource(R.string.unknown),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusIconPreview() {
    HsInterviewTheme {
        Surface {
            StatusIcon("Alive")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatusIconPreviewDark() {
    HsInterviewTheme {
        Surface {
            StatusIcon("Alive")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusIconPreviewDead() {
    HsInterviewTheme {
        Surface {
            StatusIcon("Dead")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatusIconPreviewDeadDark() {
    HsInterviewTheme {
        Surface {
            StatusIcon("Dead")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatusIconPreviewUnknown() {
    HsInterviewTheme {
        Surface {
            StatusIcon("unknown")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StatusIconPreviewUnknownDark() {
    HsInterviewTheme {
        Surface {
            StatusIcon("unknown")
        }
    }
}
