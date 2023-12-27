package com.example.leagueapp.ui.homeScreen

sealed interface HomeScreenState {

    object Loading : HomeScreenState

    object Error : HomeScreenState

    object Success : HomeScreenState
}