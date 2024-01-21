package com.example.pokemon.data

import com.example.pokemon.model.Pokemon
import com.example.pokemon.network.PokemonApiService

/**
 * Here is the Repository, it's used to manage data in a centralized way.
 */
interface PokemonRepository {
    /** Fetches list of [Pokemon] from the API */
    suspend fun getPokemons(): List<Pokemon>
}

/**
 * Network Implementation of Repository that fetch pokémons from the API.
 */
class NetworkPokemonRepository(
    private val pokemonApiService: PokemonApiService
) : PokemonRepository {
    /** Fetches list of [Pokemon] from the API*/
    override suspend fun getPokemons(): List<Pokemon> = pokemonApiService.getPokemons()
}
