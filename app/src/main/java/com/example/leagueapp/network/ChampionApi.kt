package com.example.leagueapp.network

import com.example.leagueapp.model.ChampionMin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ChampionApi(
    val id: String,
    val name: String
)

fun ChampionApi.asDomainObject() : ChampionMin {
    return ChampionMin(this.id, this.name)
}

