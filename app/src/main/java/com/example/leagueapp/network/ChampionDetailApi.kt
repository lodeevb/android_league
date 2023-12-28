package com.example.leagueapp.network

import com.example.leagueapp.model.ChampionDetail
import com.example.leagueapp.model.ChampionMin
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDetailApi(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
)

fun ChampionDetailApi.asDomainObject() : ChampionDetail {
    return ChampionDetail(this.id, this.name,this.title, this.lore)
}