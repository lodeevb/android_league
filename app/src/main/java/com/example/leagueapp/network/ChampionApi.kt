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

fun Flow<List<ChampionApi>>.asDomainObjects(): Flow<List<ChampionMin>> {
    return map {
        it.asDomainObjects()
    }
}

fun List<ChampionApi>.asDomainObjects(): List<ChampionMin> {
    var domainList = this.map {
        ChampionMin(it.id, it.name)
    }
    return domainList
}

