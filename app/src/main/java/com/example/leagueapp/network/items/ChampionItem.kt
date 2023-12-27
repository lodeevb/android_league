package com.example.leagueapp.network.items

import com.example.leagueapp.network.ChampionApi
import kotlinx.serialization.Serializable

@Serializable
data class ChampionItem(
    val champions: List<ChampionApi>,
    val count: Int
)
