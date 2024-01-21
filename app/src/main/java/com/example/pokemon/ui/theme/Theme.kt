package com.example.pokemon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColors = lightColorScheme(
    primary = HintOfPensive,
    secondary = Color.Black,
    surface = LynxWhite
)

val DarkColors = darkColorScheme(
    primary = ElectroMagnetic,
    secondary = Color.White,
    surface = BlueNights
)

@Composable
fun PokemonTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    var colorScheme = LightColors;
    if (darkTheme) colorScheme = DarkColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

