package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository

class InsertFavoritePokemon(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon) {
        repository.insertFavoritePokemon(pokemon)
    }
}