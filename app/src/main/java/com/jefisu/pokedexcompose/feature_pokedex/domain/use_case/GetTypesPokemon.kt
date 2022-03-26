package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.TypeEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository

class GetTypesPokemon(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): Resource<List<TypeEntry>> {
        return repository.getTypes()
    }
}