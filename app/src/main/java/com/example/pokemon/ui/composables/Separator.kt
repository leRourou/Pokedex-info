package com.example.pokemon.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * A simple separator composable.
 */
@Composable
fun Separator(size: Dp) {
    Row(modifier = Modifier.padding(top=size)) {}
}