package com.alwan.pokeapp.core.di

import com.alwan.pokeapp.core.data.PokemonRepository
import com.alwan.pokeapp.core.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(pokemonRepository: PokemonRepository): IPokemonRepository

}