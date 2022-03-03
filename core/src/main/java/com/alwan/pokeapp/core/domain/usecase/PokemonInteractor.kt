package com.alwan.pokeapp.core.domain.usecase

import com.alwan.pokeapp.core.data.Resource
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.core.domain.repository.IPokemonRepository
import io.reactivex.Flowable
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val pokemonRepository: IPokemonRepository) : PokemonUseCase {
    override fun getListPokemon(): Flowable<Resource<List<Pokemon>>> =
        pokemonRepository.getListPokemon()
}