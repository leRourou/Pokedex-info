package com.example.pokemon.model

import kotlinx.serialization.Serializable

/**
 * The Pokémon model class
 */
@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val type: List<String>,
    val description: String,
    val image_url: String,
    val evolutions : Evolution
) {

    /**
     * Get the main [PokemonType] of the [Pokemon]}
     */
    fun getType(): PokemonType {
        return TypeData.getValue(this.type[0]);
    }

    /**
     * Get the secondary [PokemonType] of the [Pokemon]
     */
    fun getSecondaryType(): PokemonType? {
        if (type.size < 2) return null;
        return TypeData.getValue(this.type[1])
    }

    /**
     * We use companion object to provide static functions callable without having
     * to instanciate the class.
     */
    companion object {
        /**
         * We use built-in getter/setter syntax of Kotlin.
         */
        private var pokemons: List<Pokemon> = emptyList()

        fun getPokemons(): List<Pokemon> {
            return this.pokemons
        }

        fun setPokemons(pokemons: List<Pokemon>) {
            this.pokemons = pokemons
        }

        /**
         * Returns a [Pokemon] by his ID
         */
        private fun getPokemonById(id: Int): Pokemon? {
            return pokemons.find { it.id == id}
        }

        /**
         * Get a [List<Pokemon>] of evolutions that precede the [Pokemon]
         */
        fun getPokemonsBefore(evolution: Evolution): List<Pokemon> {
            return evolution.before.mapNotNull { getPokemonById(it) }
        }

        /**
         * Get a [List<Pokemon>] of evolutions that precede the [Pokemon]
         */
        fun getPokemonsAfter(evolution: Evolution): List<Pokemon> {
            return evolution.after.mapNotNull { getPokemonById(it) }
        }
    }
}