package ai.revealtech.hsinterview.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Defines the dark color scheme for the application.
 * This includes primary, secondary, tertiary, background, and on-surface colors.
 */
private val DarkColorScheme = darkColorScheme(
  primary = Color.White,
  secondary = LightGrey,
  tertiary = Grey,
  background = DarkGrey,
  onPrimary = TextBlack,
  onSurfaceVariant = OffWhite
)

/**
 * The main theme for the HsInterview application.
 *
 * This composable function applies the [DarkColorScheme] and [Typography]
 * to the provided [content].
 *
 * @param content The composable content to which the theme will be applied.
 */
@Composable
fun HsInterviewTheme(
  content: @Composable () -> Unit
) {
  val colorScheme = DarkColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
