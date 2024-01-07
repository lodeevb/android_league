package com.example.leagueapp.network

import com.example.leagueapp.model.Spell
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing a Spell received from an API.
 *
 * @param id The unique identifier for the spell.
 * @param name The name of the spell.
 * @param description The description of the spell.
 */
@Serializable
data class SpellApi(
    val id: String,
    val name: String,
    val description: String
)

/**
 * Extension function to convert SpellApi object into a domain-specific [Spell] object.
 *
 * @param championId The ID of the champion associated with the spell.
 * @return Converted [Spell] object using the data from the SpellApi object.
 */
fun SpellApi.asDomainObject(championId: String) : Spell {
    return Spell(championId, this.id, this.name, this.description)
}