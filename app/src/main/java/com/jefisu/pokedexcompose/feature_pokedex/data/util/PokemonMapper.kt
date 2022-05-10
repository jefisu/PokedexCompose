package com.jefisu.pokedexcompose.feature_pokedex.data.util

import com.jefisu.pokedexcompose.feature_pokedex.data.local.PokemonEntity
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
        imageUrl = sprites.other.dreamWorld.frontDefault,
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

fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        imageUrl = imageUrl,
        height = height,
        types = types,
        weight = weight,
        moves = moves,
        stats = stats,
        abilities = abilities,
        id = id,
        isSelectedFavorite = isFavorite
    )
}

fun PokemonEntity.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        imageUrl = imageUrl,
        height = height,
        types = types,
        weight = weight,
        moves = moves,
        stats = stats,
        abilities = abilities,
        id = id,
        isFavorite = isSelectedFavorite
    )
}