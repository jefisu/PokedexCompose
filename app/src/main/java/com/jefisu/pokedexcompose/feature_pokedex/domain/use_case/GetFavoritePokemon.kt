package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository

class GetFavoritePokemon(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Pokemon? {
        return repository.getFavoritePokemon(name)
    }
}