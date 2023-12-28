package com.example.leagueapp.network.items

import com.example.leagueapp.network.ChampionDetailApi
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDetailData(
    val data: Map<String, ChampionDetailApi>
)