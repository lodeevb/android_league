package com.example.leagueapp.ui.favorites


/**
 * Sealed interface representing the different states of the FavoriteScreen.
 */
sealed interface FavoriteScreenState {

    /** Represents the loading state. */
    object Loading : FavoriteScreenState

    /** Represents the error state. */
    object Error : FavoriteScreenState

    /** Represents the success state. */
    object Success : FavoriteScreenState
}