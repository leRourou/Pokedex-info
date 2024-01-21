package com.example.pokemon.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokemon.R
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonType

enum class EvolutionPosition {
    FIRST,
    LAST,
    DEFAULT
}

@Composable
fun PokemonEvolutions(pokemon: Pokemon, onPokemonClicked: (pokemon: Pokemon) -> Unit) {
    val pokemonsToShow: MutableList<Pokemon> = mutableListOf()
    pokemonsToShow.addAll(Pokemon.getPokemonsBefore(pokemon.evolutions))
    pokemonsToShow.add(pokemon)
    pokemonsToShow.addAll(Pokemon.getPokemonsAfter(pokemon.evolutions))

    pokemonsToShow.forEachIndexed {index, pokemonToShow ->
        var evolutionType = EvolutionPosition.DEFAULT
        var isPokemon = false;
        if (index==0) { evolutionType = EvolutionPosition.FIRST }
        if (index == pokemonsToShow.size-1) { evolutionType = EvolutionPosition.LAST }
        if (pokemonToShow == pokemon) { isPokemon = true }
        SinglePokemonEvolution(pokemonToShow, evolutionType, isPokemon, onPokemonClicked)
    }
}

@Composable
fun SinglePokemonEvolution(
    pokemon: Pokemon,
    evolutionPosition: EvolutionPosition,
    isActualPokemon: Boolean,
    onPokemonClicked: (pokemon: Pokemon) -> kotlin.Unit
) {
    var shape = RoundedCornerShape(0.dp)
    when(evolutionPosition) {
        EvolutionPosition.FIRST -> shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        EvolutionPosition.LAST -> shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
        else -> {}
    }

    val pokemonType: PokemonType = pokemon.getType()
    var textColor: Color = pokemonType.getBackgroundColor()
    var backgroundColor: Color =pokemonType.getDefaultColor()
    if (isActualPokemon) {
        backgroundColor = pokemonType.getBackgroundColor()
        textColor = pokemonType.getTextColor()
    }

    Row(modifier = Modifier
        .height(100.dp)
        .padding(vertical = 5.dp)
        .fillMaxWidth()
        .clip(shape)
        .background(backgroundColor)
        .padding(10.dp)
        .clickable(onClick = {
            onPokemonClicked(pokemon) // On click change to the PokemonDetailScreen of the Pokémon
        }),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(pokemon.image_url)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.pokemon),
                modifier = Modifier
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = pokemon.name,
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }


    }
}