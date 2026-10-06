package cl.olimpictraining.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val OlimpicColorScheme = lightColorScheme(
    primary = OlimpicBlue,
    onPrimary = OlimpicWhite,

    secondary = OlimpicBlueDark,

    background = OlimpicBackground,
    onBackground = OlimpicTextPrimary,

    surface = OlimpicWhite,
    onSurface = OlimpicTextPrimary,

    error = OlimpicError
)

@Composable
fun OlimpicTrainingTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = OlimpicColorScheme,
        typography = Typography,
        content = content
    )
}
