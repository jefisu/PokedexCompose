package com.jefisu.pokedexcompose.feature_pokedex.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonFavorite(pokemon: PokemonEntity)

    @Delete
    suspend fun deletePokemonFavorite(pokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity WHERE name = :name")
    suspend fun getPokemonFavorite(name: String): PokemonEntity?

    @Query("SELECT * FROM pokemonentity")
    fun getPokemons(): Flow<List<PokemonEntity>>
}