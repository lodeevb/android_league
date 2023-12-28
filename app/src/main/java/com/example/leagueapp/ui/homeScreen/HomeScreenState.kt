package com.example.leagueapp.ui.homeScreen

import com.example.leagueapp.model.ChampionMin


data class ChampionListState(val championList: List<ChampionMin> = listOf())
sealed interface HomeScreenState {

    object Loading : HomeScreenState

    object Error : HomeScreenState

    object Success : HomeScreenState
}