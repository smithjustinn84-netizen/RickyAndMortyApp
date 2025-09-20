package ai.revealtech.hsinterview.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Defines the dark color scheme for the application
 */
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = White,
    secondary = DarkGray,
    onSecondary = White,
    tertiary = PrimaryColor,
    onTertiary = White,
    background = Black,
    surface = DarkGray,
    error = ErrorColor,
    onError = White,
    onBackground = White,
    onSurface = White,
    onSurfaceVariant = LightGray
)

/**
 * Defines the light color scheme for the application
 */
private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = White,
    secondary = LightGray,
    onSecondary = Black,
    tertiary = PrimaryColor,
    onTertiary = White,
    background = White,
    surface = OffWhite,
    error = ErrorColor,
    onError = White,
    onBackground = Black,
    onSurface = Black,
    onSurfaceVariant = Black
)

/**
 * The main theme for the HsInterview application.
 *
 * This composable function applies the appropriate color scheme (light or dark)
 * and [Typography] to the provided [content].
 *
 * @param darkTheme Whether the theme should be dark or light. Defaults to system setting.
 * @param content The composable content to which the theme will be applied.
 */
@Composable
fun HsInterviewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
