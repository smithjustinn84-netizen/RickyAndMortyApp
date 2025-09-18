package ai.revealtech.hsinterview.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = Color.White,
  secondary = LightGrey,
  tertiary = Grey,
  background = DarkGrey,
  onPrimary = TextBlack,
  onSurfaceVariant = OffWhite
)

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