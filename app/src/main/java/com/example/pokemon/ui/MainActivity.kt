package com.example.pokemon.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.pokemon.ui.theme.PokemonTheme

/**
 * The root of the App.
 * We use [PokemonTheme] as parent composable of PokemonApp to provide
 * dark mode and MUI features.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            var isDarkMode: Boolean by remember { mutableStateOf(false) }
            PokemonTheme(isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PokemonApp(
                        isDarkMode = isDarkMode,
                        switchDarkMode = {isDarkMode = !isDarkMode}
                    )
                }
            }
        }
    }
}