package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.MoveEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository

class GetMoves(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int, offset: Int): Resource<List<MoveEntry>> {
        return repository.getMoves(limit, offset)
    }
}