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

/**
 * ViewModel class responsible for managing data for the [HomeScreen].
 *
 * @property championRepository The [repository][ChampionRepository] for retrieving champion data.
 */
class HomeScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {

    /** The state of the HomeScreen. */
    var homeScreenState: HomeScreenState by mutableStateOf(HomeScreenState.Loading)
        private set

    /** StateFlow representing the list of champions. */
    lateinit var championListState: StateFlow<ChampionListState>

    init {
        getRepoChampions()
    }

    /**
     * Retrieves champion data from the repository and updates the state.
     */
    private fun getRepoChampions() {
        try {
            championListState = championRepository.getChampions().map {
                ChampionListState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ChampionListState(),
            )
            homeScreenState = HomeScreenState.Success
        } catch (e: Exception) {
            homeScreenState = HomeScreenState.Error
        }
    }

    /**
     * Companion object providing a [ViewModelProvider.Factory] for creating instances of [HomeScreenViewModel].
     */
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