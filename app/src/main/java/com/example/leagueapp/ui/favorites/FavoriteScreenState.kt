package com.example.leagueapp.ui.favorites

import com.example.leagueapp.model.ChampionMin

data class ChampionListState(val championList: List<ChampionMin> = listOf())
sealed interface FavoriteScreenState {

    object Loading : FavoriteScreenState

    object Error : FavoriteScreenState

    object Success : FavoriteScreenState
}