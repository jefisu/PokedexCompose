package com.jefisu.pokedexcompose.feature_pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jefisu.pokedexcompose.feature_pokedex.data.util.PokemonConverters

@TypeConverters(PokemonConverters::class)
@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract val dao: PokemonDao
}