package com.jefisu.pokedexcompose.feature_pokedex.data.remote

import com.jefisu.pokedexcompose.feature_pokedex.data.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("type")
    suspend fun getTypes(): TypeResponse

    @GET("move/{name}")
    suspend fun getMove(
        @Path("name") name: String
    ): MoveResponse

    @GET("move")
    suspend fun getMoves(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): MoveListResponse
}