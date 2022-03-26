package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository

class GetPokemonByName(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Resource<Pokemon> {
        return repository.getPokemon(name)
    }
}