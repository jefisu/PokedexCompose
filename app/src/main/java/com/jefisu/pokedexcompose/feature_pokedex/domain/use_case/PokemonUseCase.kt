package com.jefisu.pokedexcompose.feature_pokedex.domain.use_case

data class PokemonUseCase(
    val getPokemonByName: GetPokemonByName,
    val getPokemons: GetPokemons,
    val getTypesPokemon: GetTypesPokemon,
    val getMoves: GetMoves,
    val insertFavoritePokemon: InsertFavoritePokemon,
    val deleteFavoritePokemon: DeleteFavoritePokemon,
    val getFavoritePokemon: GetFavoritePokemon,
    val getFavoritePokemons: GetFavoritePokemons
)
