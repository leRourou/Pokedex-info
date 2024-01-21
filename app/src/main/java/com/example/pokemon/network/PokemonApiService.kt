package com.example.pokemon.network

import com.example.pokemon.model.Pokemon
import retrofit2.http.GET

/**
 *  This interface define the HTTP Reuqest method and route
 */
interface PokemonApiService {
    @GET("pokemons.json")
    suspend fun getPokemons(): List<Pokemon>
}
