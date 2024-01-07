package com.example.leagueapp.ui.homeScreen

import com.example.leagueapp.model.ChampionMin


/**
 * Represents the state of a list of champions.
 *
 * @property championList The list of [ChampionMin] objects.
 */
data class ChampionListState(val championList: List<ChampionMin> = listOf())

/**
 * Sealed interface representing the different states of the HomeScreen.
 */
sealed interface HomeScreenState {

    /** Represents the loading state. */
    object Loading : HomeScreenState

    /** Represents the error state. */
    object Error : HomeScreenState

    /** Represents the success state. */
    object Success : HomeScreenState
}