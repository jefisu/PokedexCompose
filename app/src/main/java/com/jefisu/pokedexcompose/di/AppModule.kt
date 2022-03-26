package com.jefisu.pokedexcompose.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.jefisu.pokedexcompose.core.util.Constants
import com.jefisu.pokedexcompose.feature_pokedex.data.local.PokemonDatabase
import com.jefisu.pokedexcompose.feature_pokedex.data.remote.PokemonApi
import com.jefisu.pokedexcompose.feature_pokedex.data.repository.PokemonRepositoryImpl
import com.jefisu.pokedexcompose.feature_pokedex.data.util.GsonParser
import com.jefisu.pokedexcompose.feature_pokedex.data.util.PokemonConverters
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository
import com.jefisu.pokedexcompose.feature_pokedex.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            "pokemon_db"
        ).addTypeConverter(PokemonConverters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(api: PokemonApi, db: PokemonDatabase): PokemonRepository {
        return PokemonRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun providePokemonUseCase(repository: PokemonRepository): PokemonUseCase {
        return PokemonUseCase(
            getPokemonByName = GetPokemonByName(repository),
            getPokemons = GetPokemons(repository),
            getTypesPokemon = GetTypesPokemon(repository),
            getMoves = GetMoves(repository),
            insertFavoritePokemon = InsertFavoritePokemon(repository),
            deleteFavoritePokemon = DeleteFavoritePokemon(repository),
            getFavoritePokemon = GetFavoritePokemon(repository),
            getFavoritePokemons = GetFavoritePokemons(repository)
        )
    }
}
