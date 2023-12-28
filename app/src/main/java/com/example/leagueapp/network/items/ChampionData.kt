package com.example.leagueapp.network.items

import com.example.leagueapp.network.ChampionApi
import kotlinx.serialization.Serializable

@Serializable
data class ChampionData(
    val data: Map<String, ChampionApi>
)