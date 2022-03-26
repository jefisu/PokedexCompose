package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritePokemons(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<List<Pokemon>> {
        return repository.getFavoritePokemons()
    }
}