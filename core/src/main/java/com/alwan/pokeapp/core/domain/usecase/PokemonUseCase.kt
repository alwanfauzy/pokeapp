package com.alwan.pokeapp.core.domain.usecase

import com.alwan.pokeapp.core.data.Resource
import com.alwan.pokeapp.core.domain.model.Pokemon
import io.reactivex.Flowable

interface PokemonUseCase {
    fun getListPokemon(): Flowable<Resource<List<Pokemon>>>
}