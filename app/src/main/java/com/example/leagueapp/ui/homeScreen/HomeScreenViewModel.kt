package com.example.leagueapp.ui.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.leagueapp.LeagueApplication
import com.example.leagueapp.data.ChampionRepository
import com.example.leagueapp.model.ChampionMin
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {

    var homeScreenState: HomeScreenState by mutableStateOf(HomeScreenState.Loading)
        private set

    lateinit var championState: StateFlow<List<ChampionMin>>

    init {
        getRepoChampions()
    }

    private fun getRepoChampions() {
        try {
            viewModelScope.launch { championRepository.refresh() }
            championState = championRepository.getChampions().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf(),
            )
            homeScreenState = HomeScreenState.Success
        } catch (e: IOException) {
            homeScreenState = HomeScreenState.Error
        }
    }

    // object to tell the android framework how to handle the parameter of the viewmodel
    companion object {
        private var Instance: HomeScreenViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[APPLICATION_KEY] as LeagueApplication)
                    val championRepository = application.container.championRepository
                    Instance = HomeScreenViewModel(championRepository = championRepository)
                }
                Instance!!
            }
        }
    }
}