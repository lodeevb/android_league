package com.example.leagueapp.network

import com.example.leagueapp.model.ChampionMin
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing champions received from an API.
 *
 * @property id Unique identifier for the champion.
 * @property name Name of the champion.
 * @property isFavorite Boolean indicitating of the champion is a favorite.
 */
@Serializable
data class ChampionApi(
    val id: String,
    val name: String,
    val isFavorite: Boolean,
)

/**
 * Extension function to convert ChampionApi object into a domain-specific [ChampionMin] object.
 *
 * @return [ChampionMin] object representing the champion's details.
 */
fun ChampionApi.asDomainObject() : ChampionMin {
    return ChampionMin(this.id, this.name, this.isFavorite)
}

