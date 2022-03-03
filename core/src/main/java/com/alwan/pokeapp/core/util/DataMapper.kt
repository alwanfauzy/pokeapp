package com.alwan.pokeapp.core.util

import com.alwan.pokeapp.core.data.local.entity.PokemonEntity
import com.alwan.pokeapp.core.data.remote.response.*
import com.alwan.pokeapp.core.domain.model.Pokemon

object DataMapper {
    fun mapResponsesToEntities(input: List<PokemonDetailResponse>): List<PokemonEntity> {
        val result = ArrayList<PokemonEntity>()
        input.map {
            val stat = convertStatsToMap(it.stats)
            val pokemon = PokemonEntity(
                id = it.id,
                name = it.name,
                weight = it.weight,
                height = it.height,
                statHp = stat["hp"],
                statAttack = stat["attack"],
                statDefense = stat["defense"],
                statSpecialAttack = stat["special-attack"],
                statSpecialDefense = stat["special-defense"],
                statSpeed = stat["speed"],
                abilities = convertAbilitiesToString(it.abilities),
                frontSprite = it.sprites?.frontDefault,
                backSprite = it.sprites?.backDefault,
            )
            result.add(pokemon)
        }
        return result
    }

    fun mapEntitiesToDomain(input: List<PokemonEntity>): List<Pokemon> {
        val result = ArrayList<Pokemon>()
        input.map {
            val pokemon = Pokemon(
                id = it.id,
                name = it.name,
                weight = it.weight,
                height = it.height,
                statHp = it.statHp,
                statAttack = it.statAttack,
                statDefense = it.statDefense,
                statSpecialAttack = it.statSpecialAttack,
                statSpecialDefense = it.statSpecialDefense,
                statSpeed = it.statSpeed,
                abilities = it.abilities,
                frontSprite = it.frontSprite,
                backSprite = it.backSprite,
            )
            result.add(pokemon)
        }
        return result
    }

    private fun convertAbilitiesToString(abilities: List<AbilitiesResponse>?): String {
        var result = ""
        abilities?.forEachIndexed { index, ability ->
            val name = ability.ability?.name
            result += if (index != abilities.size - 1) {
                "$name, "
            } else {
                "$name"
            }
        }

        return result
    }

    private fun convertStatsToMap(stats: List<StatsResponse>?): HashMap<String?, Int?> {
        val result = HashMap<String?, Int?>()
        stats?.forEach { stat ->
            val name = stat.stat?.name
            /*
                name = hp, attack, defense, special-attack, special-defense, speed
             */

            val baseStat = stat.baseStat

            result[name] = baseStat
        }

        return result
    }

    private fun convertChainToString(chain: ChainResponse?): String {
        var result = ""
        var head = chain

        while (head?.evolvesTo != null) {
            result += "${head.species} -> "
            head = chain?.evolvesTo?.get(0)
        }

        return result
    }
}