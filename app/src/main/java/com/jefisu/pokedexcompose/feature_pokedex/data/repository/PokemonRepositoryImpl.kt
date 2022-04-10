package com.jefisu.pokedexcompose.feature_pokedex.data.repository

import com.jefisu.pokedexcompose.core.util.Resource
import com.jefisu.pokedexcompose.core.util.httpRequest
import com.jefisu.pokedexcompose.feature_pokedex.data.local.PokemonDatabase
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.PokemonApi
import com.jefisu.pokedexcompose.feature_pokedex.data.util.toMoveEntry
import com.jefisu.pokedexcompose.feature_pokedex.data.util.toPokemon
import com.jefisu.pokedexcompose.feature_pokedex.data.util.toPokemonEntity
import com.jefisu.pokedexcompose.feature_pokedex.data.util.toTypeEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.MoveEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.TypeEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val db: PokemonDatabase
) : PokemonRepository {

    private val dao = db.dao

    override suspend fun getPokemon(name: String): Resource<Pokemon> {
        return httpRequest {
            api.getPokemonByName(name).toPokemon()
        }
    }

    override suspend fun getPokemons(limit: Int, offset: Int): Resource<List<Pokemon>> {
        return httpRequest {
            val pokemons = api.getPokemons(limit, offset)
            pokemons.results.map {
                api.getPokemonByName(it.name).toPokemon()
            }
        }
    }

    override suspend fun getTypes(): Resource<List<TypeEntry>> {
        return httpRequest {
            val types = api.getTypes().results
            types.map { it.toTypeEntry() }
        }
    }

    override suspend fun getMoves(limit: Int, offset: Int): Resource<List<MoveEntry>> {
        return httpRequest {
            val moves = api.getMoves(limit, offset)
            moves.results.map {
                api.getMove(it.name).toMoveEntry()
            }
        }
    }

    override fun getFavoritePokemons(): Flow<List<Pokemon>> {
        return dao.getPokemons().map { pokemons ->
            pokemons.map { it.toPokemon() }
        }
    }

    override suspend fun getFavoritePokemon(name: String): Pokemon? {
        return dao.getPokemonFavorite(name)?.toPokemon()
    }

    override suspend fun insertFavoritePokemon(pokemon: Pokemon) {
        dao.insertPokemonFavorite(pokemon.toPokemonEntity())
    }

    override suspend fun deleteFavoritePokemon(pokemon: Pokemon) {
        dao.deletePokemonFavorite(pokemon.toPokemonEntity())
    }
}