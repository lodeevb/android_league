package com.example.leagueapp.network

import com.example.leagueapp.model.ChampionDetail
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDetailApi(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val spells: List<SpellApi>
)

fun ChampionDetailApi.asDomainObject() : ChampionDetail {
    return ChampionDetail(this.id, this.name,this.title, this.lore)
}