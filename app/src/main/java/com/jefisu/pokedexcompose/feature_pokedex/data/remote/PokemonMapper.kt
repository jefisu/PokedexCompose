package com.jefisu.pokedexcompose.feature_pokedex.data.remote

import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.MoveResponse
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.PokemonResponse
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.ResultX
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.MoveEntry
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.Pokemon
import com.jefisu.pokedexcompose.feature_pokedex.domain.model.TypeEntry
import com.jefisu.pokedexcompose.feature_pokedex.util.parseTypeToColor

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        imageUrl = sprites.other.officialArtwork.frontDefault,
        height = height,
        types = types.map { it.type.name },
        weight = weight,
        moves = moves.map { it.move.name },
        stats = stats,
        abilities = abilities.map { it.ability.name },
        id = id
    )
}

fun ResultX.toTypeEntry(): TypeEntry {
    return TypeEntry(
        name = name,
        color = parseTypeToColor(name)
    )
}

fun MoveResponse.toMoveEntry(): MoveEntry {
    return MoveEntry(
        name = name,
        type = type.name,
        id = id
    )
}