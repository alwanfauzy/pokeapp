package com.alwan.pokeapp.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "weight")
    val weight: Int?,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "stat_hp")
    val statHp: Int?,

    @ColumnInfo(name = "stat_attack")
    val statAttack: Int?,

    @ColumnInfo(name = "stat_defense")
    val statDefense: Int?,

    @ColumnInfo(name = "stat_special_attack")
    val statSpecialAttack: Int?,

    @ColumnInfo(name = "stat_special_defense")
    val statSpecialDefense: Int?,

    @ColumnInfo(name = "stat_speed")
    val statSpeed: Int?,

    @ColumnInfo(name = "abilities")
    val abilities: String?,

    @ColumnInfo(name = "front_sprite")
    val frontSprite: String?,

    @ColumnInfo(name = "back_sprite")
    val backSprite: String?,
)
