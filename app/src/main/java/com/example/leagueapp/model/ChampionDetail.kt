package com.example.leagueapp.model

/**
 * Represents detailed information about a champion.
 * @property id The unique identifier for the champion.
 * @property name The name of the champion.
 * @property title The title associated with the champion.
 * @property lore The background lore or story of the champion.
 */
data class ChampionDetail(val id: String, val name: String, val title: String, val lore: String)