package com.example.leagueapp.network.items

import com.example.leagueapp.network.ChampionApi
import kotlinx.serialization.Serializable

/**
 * Represents the data retrieved for a specific champion's details from the API.
 * @property data A map containing champion IDs as keys and their associated [ChampionApi] as values.
 */
@Serializable
data class ChampionData(
    val data: Map<String, ChampionApi>
)