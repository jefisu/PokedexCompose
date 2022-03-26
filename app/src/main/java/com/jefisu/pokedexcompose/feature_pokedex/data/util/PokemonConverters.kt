package com.jefisu.pokedexcompose.feature_pokedex.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.Stat

@ProvidedTypeConverter
class PokemonConverters(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return jsonParser.fromJson(
            json,
            object : TypeToken<ArrayList<String>>() {}.type
        )
    }

    @TypeConverter
    fun toJson(types: List<String>): String {
        return jsonParser.toJson(
            types,
            object : TypeToken<ArrayList<String>>() {}.type
        )
    }

    @TypeConverter
    fun fromStatsJson(json: String): List<Stat> {
        return jsonParser.fromJson(
            json,
            object : TypeToken<ArrayList<Stat>>() {}.type
        )
    }

    @TypeConverter
    fun toStatsJson(stats: List<Stat>): String {
        return jsonParser.toJson(
            stats,
            object : TypeToken<ArrayList<Stat>>() {}.type
        )
    }
}