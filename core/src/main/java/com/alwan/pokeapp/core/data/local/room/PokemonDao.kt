package com.alwan.pokeapp.core.data.local.room

import androidx.room.*
import com.alwan.pokeapp.core.data.local.entity.PokemonEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getListPokemon(): Flowable<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: List<PokemonEntity>): Completable

    @Delete
    fun deletePokemon(pokemon: PokemonEntity): Int
}