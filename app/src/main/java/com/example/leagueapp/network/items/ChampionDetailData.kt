package com.example.leagueapp.network.items

import com.example.leagueapp.network.ChampionDetailApi
import kotlinx.serialization.Serializable

/**
 * Represents the data retrieved for a specific champion's details from the API.
 * @property data A map containing champion IDs as keys and their associated [ChampionDetailApi] as values.
 */
@Serializable
data class ChampionDetailData(
    val data: Map<String, ChampionDetailApi>
)