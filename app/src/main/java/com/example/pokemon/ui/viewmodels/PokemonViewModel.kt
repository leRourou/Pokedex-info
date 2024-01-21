package com.example.pokemon.ui.viewmodels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokemon.PokedexApplication
import com.example.pokemon.data.PokemonRepository
import com.example.pokemon.model.Pokemon
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Pokédex Home Screen
 */
sealed interface PokemonUiState {
    data class Success(val pokemon: List<Pokemon>) : PokemonUiState
    data object Error : PokemonUiState
    data object Loading : PokemonUiState
}

class PokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {
    var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
    private set

    init {
        getPokemons()
    }

    public fun getPokemons() {
        viewModelScope.launch {
            pokemonUiState = PokemonUiState.Loading
            pokemonUiState = try {
                val pokemons = pokemonRepository.getPokemons()
                Pokemon.setPokemons(pokemons)
                PokemonUiState.Success(pokemons)
            } catch (e: IOException) {
                PokemonUiState.Error
            } catch (e: HttpException) {
                PokemonUiState.Error
            }
        }
    }


    /**
     * Factory for [PokemonViewModel] that takes [PokemonRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokedexApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}