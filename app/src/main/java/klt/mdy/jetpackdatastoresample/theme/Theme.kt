package klt.mdy.jetpackdatastoresample.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Teal900,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Teal200,
    secondary = Teal900,
)

@Composable
fun JetpackDataStoreSampleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    with(systemUiController) {
//        this.isStatusBarVisible = false
//        this.isSystemBarsVisible = false
        this.setStatusBarColor(
            color = MaterialTheme.colors.primary
        )
        this.setSystemBarsColor(
            color = MaterialTheme.colors.primary
        )
    }
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (darkTheme) Teal900 else Teal200
        )
    }
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}