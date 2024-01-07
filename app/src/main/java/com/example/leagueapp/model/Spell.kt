package com.example.leagueapp.model

/**
 * Represents a spell associated with a champion.
 * @property championId The ID of the champion to which this spell belongs.
 * @property id The unique identifier for the spell.
 * @property name The name of the spell.
 * @property description The description or details of the spell's functionality.
 */
data class Spell(val championId: String, val id: String, val name: String, val description: String)