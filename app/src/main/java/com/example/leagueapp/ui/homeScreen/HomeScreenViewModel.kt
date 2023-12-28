package com.example.leagueapp.ui.homeScreen

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {

    var homeScreenState: HomeScreenState by mutableStateOf(HomeScreenState.Loading)
        private set

    lateinit var championListState: StateFlow<ChampionListState>

    init {
        getRepoChampions()
    }

    private fun getRepoChampions() {
        try {
            viewModelScope.launch { championRepository.refresh() }
            championListState = championRepository.getChampions().map {
                ChampionListState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ChampionListState(),
            )
            homeScreenState = HomeScreenState.Success
        } catch (e: IOException) {
            homeScreenState = HomeScreenState.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LeagueApplication)
                val championRepository = application.container.championRepository
                HomeScreenViewModel(
                    championRepository = championRepository,
                )
            }
        }
    }
}