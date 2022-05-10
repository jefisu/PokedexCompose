package com.jefisu.pokedexcompose.feature_pokedex.util

import androidx.compose.ui.graphics.Color
import com.jefisu.pokedexcompose.R
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.Stat
import com.jefisu.pokedexcompose.ui.theme.*


fun parseTypeToColor(type: String): Color {
    return when (type.lowercase()) {
        "normal" -> NormalType
        "fire" -> FireType
        "water" -> WaterType
        "electric" -> ElectricType
        "grass" -> GrassType
        "ice" -> IceType
        "fighting" -> FightingType
        "poison" -> PoisonType
        "ground" -> GroundType
        "flying" -> FlyingType
        "psychic" -> PsychicType
        "bug" -> BugType
        "rock" -> RockType
        "ghost" -> GhostType
        "dragon" -> DragonType
        "dark" -> DarkType
        "steel" -> SteelType
        "fairy" -> FairyType
        else -> Color.Black
    }
}

fun parseTypeToImage(type: String): Int {
    return when (type.lowercase()) {
        "normal" -> R.drawable.ic_normal
        "fire" -> R.drawable.ic_fire
        "water" -> R.drawable.ic_water
        "electric" -> R.drawable.ic_electric
        "grass" -> R.drawable.ic_grass
        "ice" -> R.drawable.ic_ice
        "fighting" -> R.drawable.ic_fighting
        "poison" -> R.drawable.ic_poison
        "ground" -> R.drawable.ic_ground
        "flying" -> R.drawable.ic_flying
        "psychic" -> R.drawable.ic_psychic
        "bug" -> R.drawable.ic_bug
        "rock" -> R.drawable.ic_rock
        "ghost" -> R.drawable.ic_ghost
        "dragon" -> R.drawable.ic_dragon
        "dark" -> R.drawable.ic_dark
        "steel" -> R.drawable.ic_steel
        "fairy" -> R.drawable.ic_fairy
        else -> R.drawable.ic_unknown
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when (stat.stat.name.lowercase()) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SPATK"
        "special-defense" -> "SPDEF"
        "speed" -> "SPD"
        else -> ""
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}