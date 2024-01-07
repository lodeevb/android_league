package com.example.leagueapp.model

/**
 * Represents essential information about a champion in a minimized form.
 * @property id The unique identifier for the champion.
 * @property name The name of the champion.
 * @property isFavorite A flag indicating whether the champion is marked as a favorite (default value: false).
 */
data class ChampionMin(val id: String, val name: String, val isFavorite: Boolean = false)