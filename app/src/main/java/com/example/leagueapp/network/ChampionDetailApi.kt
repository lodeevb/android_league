package com.example.leagueapp.network

import com.example.leagueapp.model.ChampionDetail
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a champion's information received from an API.
 *
 * @property id Unique identifier for the champion.
 * @property name Name of the champion.
 * @property title Title of the champion.
 * @property lore Lore or background story of the champion.
 * @property spells List of [SpellApi] representing spells associated with the champion.
 */
@Serializable
data class ChampionDetailApi(
    val id: String,
    val name: String,
    val title: String,
    val lore: String,
    val spells: List<SpellApi>
)

/**
 * Extension function to convert ChampionDetailApi object into a domain-specific [ChampionDetail] object.
 *
 * @return [ChampionDetail] object representing the champion's details.
 */
fun ChampionDetailApi.asDomainObject() : ChampionDetail {
    return ChampionDetail(this.id, this.name,this.title, this.lore)
}