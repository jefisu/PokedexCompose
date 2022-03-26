package com.jefisu.pokedexcompose.feature_pokedex.domain.repository

import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.MoveEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.TypeEntry
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemon(name: String): Resource<Pokemon>

    suspend fun getPokemons(limit: Int, offset: Int): Resource<List<Pokemon>>

    suspend fun getTypes(): Resource<List<TypeEntry>>

    suspend fun getMoves(limit: Int, offset: Int): Resource<List<MoveEntry>>

    fun getFavoritePokemons(): Flow<List<Pokemon>>

    suspend fun getFavoritePokemon(name: String): Pokemon?

    suspend fun insertFavoritePokemon(pokemon: Pokemon)

    suspend fun deleteFavoritePokemon(pokemon: Pokemon)
}