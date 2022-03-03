package com.alwan.pokeapp.core.data

import com.alwan.pokeapp.core.data.local.LocalDataSource
import com.alwan.pokeapp.core.data.remote.RemoteDataSource
import com.alwan.pokeapp.core.data.remote.network.ApiResponse
import com.alwan.pokeapp.core.data.remote.response.PokemonDetailResponse
import com.alwan.pokeapp.core.domain.model.Pokemon
import com.alwan.pokeapp.core.domain.repository.IPokemonRepository
import com.alwan.pokeapp.core.util.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IPokemonRepository {
    override fun getListPokemon(): Flowable<Resource<List<Pokemon>>> = object :
        NetworkBoundResource<List<Pokemon>, List<PokemonDetailResponse>>() {
        override fun loadFromDB(): Flowable<List<Pokemon>> {
            return localDataSource.getListPokemon().map { DataMapper.mapEntitiesToDomain(it) }
        }

        override fun shouldFetch(data: List<Pokemon>?): Boolean = data.isNullOrEmpty()

        override fun createCall(): Flowable<ApiResponse<List<PokemonDetailResponse>>> =
            remoteDataSource.getListPokemon()

        override fun saveCallResult(data: List<PokemonDetailResponse>) {
            val listPokemon = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertPokemon(listPokemon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

    }.asFlowable()
}