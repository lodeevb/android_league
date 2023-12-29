package com.example.leagueapp.ui.detailScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.leagueapp.LeagueApplication
import com.example.leagueapp.data.ChampionRepository
import com.example.leagueapp.ui.homeScreen.ChampionListState
import com.example.leagueapp.ui.homeScreen.HomeScreenState
import com.example.leagueapp.ui.homeScreen.HomeScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class DetailScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {

    var detailScreenState: DetailScreenState by mutableStateOf(DetailScreenState.Loading)
        private set

    private val _detailChampionState = MutableStateFlow(ChampionDetailState(/* Initial value */))
    val detailChampionState: StateFlow<ChampionDetailState> = _detailChampionState

    fun fetchChampionDetails(championId: String) {
        try {
            viewModelScope.launch {
                championRepository.refreshDetails(championId)
                val championDetail = championRepository.getChampionDetail(championId).first()
                _detailChampionState.value = ChampionDetailState(championDetail)
                detailScreenState = DetailScreenState.Success
            }
        }
        catch (e: IOException) {
            detailScreenState = DetailScreenState.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LeagueApplication)
                val championRepository = application.container.championRepository
                DetailScreenViewModel(
                    championRepository = championRepository,
                )
            }
        }
    }
}