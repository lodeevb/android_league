package com.example.leagueapp.ui.detailScreen

import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpells
import com.example.leagueapp.model.ChampionDetail

data class ChampionDetailState(val champion: ChampionWithSpells = ChampionWithSpells(
    ChampionDetail("","","",""),
    listOf(),
    ))

sealed interface DetailScreenState {

    object Loading : DetailScreenState

    object Error : DetailScreenState

    object Success : DetailScreenState
}