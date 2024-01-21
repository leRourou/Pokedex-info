package com.example.pokemon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pokemon.R
import com.example.pokemon.model.Pokemon
import com.example.pokemon.ui.composables.PokemonList
import com.example.pokemon.ui.viewmodels.PokemonUiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Pokédex app home page
 */
@Composable
fun PokedexHome(
    pokemonUiState: PokemonUiState,
    modifier: Modifier = Modifier,
    onPokemonClicked: (Pokemon) -> Unit,
    retryAction: () -> Unit,
    reloadPokemons: () -> Unit
    ) {

    // We are using com.google.accompanist.swiperefresh library
    val isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    SwipeRefresh(
        onRefresh = {
            reloadPokemons()
        },
        state = swipeRefreshState,
        ) {
        when (pokemonUiState) {
            is PokemonUiState.Loading -> LoadingScreen(modifier = modifier)
            is PokemonUiState.Success -> PokemonList(
                pokemons = pokemonUiState.pokemon,
                modifier = modifier,
                onPokemonClicked = onPokemonClicked
            )
            is PokemonUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
            else -> {}
        }
    }
}

/**
 * Loading page. Shows per default on data fetch.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * Error page. Shows if an error occurs while data fetch.
 * For example when the phone is not connected to Internet.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

