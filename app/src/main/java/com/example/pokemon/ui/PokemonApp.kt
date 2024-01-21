package com.example.pokemon.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemon.R
import com.example.pokemon.model.Pokemon
import com.example.pokemon.ui.screens.PokedexHome
import com.example.pokemon.ui.screens.PokemonDetailsScreen
import com.example.pokemon.ui.viewmodels.PokemonViewModel

enum class PokemonScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    PokemonDetails(title = R.string.pokemon_details)
}

/**
 * The main app file. Define all the application logic, including routes.
 */
@Composable
fun PokemonApp(
    navController: NavHostController = rememberNavController(),
    isDarkMode: Boolean,
    switchDarkMode: () -> Unit
) {
    var currentPokemon: Pokemon? by remember { mutableStateOf(null) }
    var onPokemontDetailsPage: Boolean by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { PokemonTopAppBar(
            onPokemonDetailsPage = onPokemontDetailsPage,
            navigateUp = { navController.navigate(PokemonScreen.Start.name)},
            currentPokemon = currentPokemon,
            isDarkMode = isDarkMode,
            switchDarkMode = switchDarkMode
        ) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val pokemonViewModel: PokemonViewModel =
                viewModel(factory = PokemonViewModel.Factory)
            NavHost(
                navController = navController,
                startDestination = PokemonScreen.Start.name,
            ) {
                composable(route = PokemonScreen.Start.name) {
                    onPokemontDetailsPage = false
                    PokedexHome(
                        pokemonUiState = pokemonViewModel.pokemonUiState,
                        retryAction = pokemonViewModel::getPokemons,
                        onPokemonClicked = { pokemon: Pokemon ->
                            currentPokemon = pokemon
                            navController.navigate(PokemonScreen.PokemonDetails.name)
                        },
                        reloadPokemons =  { pokemonViewModel.getPokemons() }
                    )
                }
                composable(route = PokemonScreen.PokemonDetails.name) {
                    onPokemontDetailsPage = true
                    PokemonDetailsScreen(
                        pokemon = currentPokemon!!,
                        modifier = Modifier
                            .fillMaxSize(),
                        onPokemonClicked = { pokemon: Pokemon ->
                            currentPokemon = pokemon
                            navController.navigate(PokemonScreen.PokemonDetails.name)
                        }
                    )
                }
            }
        }
    }
}

/**
 * Application Top Bar: used to switch between light and dark mode
 * and see the title of the App.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTopAppBar(
    onPokemonDetailsPage: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    currentPokemon: Pokemon?,
    isDarkMode: Boolean,
    switchDarkMode: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Override to change the background
        ),
        navigationIcon = {
            if (onPokemonDetailsPage) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        title = {
            var name = stringResource(R.string.app_name)
            if (onPokemonDetailsPage) {
                if (currentPokemon != null) {
                    name = currentPokemon.name
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { switchDarkMode() },
                    modifier = Modifier.padding(end = 20.dp)
                )
            }

        },
    )
}