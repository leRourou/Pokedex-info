package com.example.pokemon.model

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import com.example.pokemon.R

/**
 * Data of every [PokemonType], including their French Name, a color and an icon resource.
 */
val TypeData: Map<String, PokemonType> = mapOf(
    "Plante" to PokemonType("Plante", 0xFFD0F4DE, R.drawable.grass),
    "Feu" to PokemonType("Feu", 0xFFFFADAD, R.drawable.fire),
    "Eau" to PokemonType("Eau", 0xFFBDE0FE, R.drawable.water),
    "Insecte" to PokemonType("Insecte", 0xFFCCD5AE, R.drawable.bug),
    "Normal" to PokemonType("Normal", 0xFFFEFEA0, R.drawable.normal),
    "Poison" to PokemonType("Poison", 0xFFE0AED0, R.drawable.poison),
    "Vol" to PokemonType("Vol", 0xFFB9F3FC, R.drawable.flying)
)

/**
 * The [PokemonType] model class
 */
data class PokemonType(
    val name: String,
    val color: Long,
    val iconResourceId: Int
) {

    /**
     * Returns the [Color] of the Type
     */
    fun getColor(): Color {
        return Color(this.color);
    }

    /**
     * Private function that makes a color darker using brightness and saturation
     */
    private fun getDarkerColor(brightness: Float, saturation: Float): Color {
        val hsv = FloatArray(3)
        ColorUtils.colorToHSL(this.color.toInt(), hsv)
        hsv[2] *= brightness
        hsv[1] *= saturation

        return Color(ColorUtils.HSLToColor(hsv))
    }

    fun getDefaultColor(): Color {
        return getDarkerColor(0.9f, 0.8f)
    }

    fun getTextColor(): Color {
        return getDarkerColor(0.4f,0.6f)
    }

    fun getBackgroundColor(): Color {
        return getDarkerColor(0.7f, 0.8f)
    }
}