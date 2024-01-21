package com.example.pokemon.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokemon.model.Pokemon

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    modifier: Modifier = Modifier,
    onPokemonClicked: (Pokemon) -> Unit,
    ) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(4.dp),
    ) {
        items(items = pokemons, key = { pokemon -> pokemon.id }) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                modifier = modifier,
                onPokemonClicked = {
                    onPokemonClicked(it)
                })
        }
    }
}