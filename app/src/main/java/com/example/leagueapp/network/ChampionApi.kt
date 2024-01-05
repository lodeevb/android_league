package com.example.leagueapp.network

import com.example.leagueapp.model.ChampionMin
import kotlinx.serialization.Serializable

@Serializable
data class ChampionApi(
    val id: String,
    val name: String,
    val isFavorite: Boolean,
)

fun ChampionApi.asDomainObject() : ChampionMin {
    return ChampionMin(this.id, this.name, this.isFavorite)
}

