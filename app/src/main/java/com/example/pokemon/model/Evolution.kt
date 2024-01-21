package com.example.pokemon.model

import kotlinx.serialization.Serializable

/**
 * The [Evolution] model class
 */
@Serializable
data class Evolution(
    val before: List<Int>,
    val after: List<Int>,
)