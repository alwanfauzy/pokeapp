package com.alwan.pokeapp.core.data.local

import com.alwan.pokeapp.core.data.local.entity.PokemonEntity
import com.alwan.pokeapp.core.data.local.room.PokemonDao
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val pokemonDao: PokemonDao) {
    fun getListPokemon(): Flowable<List<PokemonEntity>> = pokemonDao.getListPokemon()

    fun insertPokemon(pokemon: List<PokemonEntity>): Completable = pokemonDao.insertPokemon(pokemon)

    fun deletePokemon(pokemon: PokemonEntity): Int = pokemonDao.deletePokemon(pokemon)
}