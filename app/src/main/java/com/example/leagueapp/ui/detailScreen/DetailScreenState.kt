package com.example.leagueapp.ui.detailScreen

import com.example.leagueapp.model.ChampionDetail

data class ChampionDetailState(val championDetail: ChampionDetail = ChampionDetail("","","",""))

sealed interface DetailScreenState {

    object Loading : DetailScreenState

    object Error : DetailScreenState

    object Success : DetailScreenState
}