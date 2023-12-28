package com.example.leagueapp.ui.detailScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.leagueapp.data.ChampionRepository
import kotlinx.coroutines.flow.StateFlow

class DetailScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {

    var detailScreenState: DetailScreenState by mutableStateOf(DetailScreenState.Loading)
        private set

    lateinit var championDetailState: StateFlow<ChampionDetailState>
}