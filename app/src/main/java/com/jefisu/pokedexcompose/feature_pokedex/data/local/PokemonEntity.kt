package com.jefisu.pokedexcompose.feature_pokedex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.Stat

@Entity
data class PokemonEntity(
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val moves: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>,
    @PrimaryKey val id: Int?,
    val isSelectedFavorite: Boolean = false
)
