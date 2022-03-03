package com.alwan.pokeapp.main

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.alwan.pokeapp.core.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(pokemonUseCase: PokemonUseCase) : ViewModel() {
    val listPokemon = LiveDataReactiveStreams.fromPublisher(pokemonUseCase.getListPokemon())
}