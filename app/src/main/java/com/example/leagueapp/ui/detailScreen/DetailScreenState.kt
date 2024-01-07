package com.example.leagueapp.ui.detailScreen

import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpells
import com.example.leagueapp.model.ChampionDetail

/**
 * Represents the state of the champion his details.
 *
 * @property champion The [ChampionWithSpells] object
 */
data class ChampionDetailState(val champion: ChampionWithSpells = ChampionWithSpells(
    ChampionDetail("","","",""),
    listOf(),
    ))

/**
 * Sealed interface representing the different states of the DetailScreen.
 */
sealed interface DetailScreenState {

    /** Represents the loading state. */
    object Loading : DetailScreenState

    /** Represents the error state. */
    object Error : DetailScreenState

    /** Represents the success state. */
    object Success : DetailScreenState
}