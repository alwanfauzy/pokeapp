package com.alwan.pokeapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val id: Int?,
    val name: String?,
    val weight: Int?,
    val height: Int?,
    val statHp: Int?,
    val statAttack: Int?,
    val statDefense: Int?,
    val statSpecialAttack: Int?,
    val statSpecialDefense: Int?,
    val statSpeed: Int?,
    val abilities: String?,
    val frontSprite: String?,
    val backSprite: String?,
) : Parcelable
