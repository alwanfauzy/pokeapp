package com.alwan.pokeapp.core.domain.repository

import com.alwan.pokeapp.core.data.Resource
import com.alwan.pokeapp.core.domain.model.Pokemon
import io.reactivex.Flowable

interface IPokemonRepository {
    fun getListPokemon(): Flowable<Resource<List<Pokemon>>>
}