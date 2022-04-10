package com.jefisu.pokedexcompose.di

import com.jefisu.pokedexcompose.feature_pokedex.data.repository.PokemonRepositoryImpl
import com.jefisu.pokedexcompose.feature_pokedex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        repositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}