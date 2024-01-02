package com.example.leagueapp.ui.favorites

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.io.IOException

class FavoriteScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {
    var favoriteScreenState: FavoriteScreenState by mutableStateOf(FavoriteScreenState.Loading)
        private set

    lateinit var championListState: StateFlow<ChampionListState>

    init {
        getFavoriteChampions()
    }

    private fun getFavoriteChampions() {
        try {
            championListState = championRepository.getFavoriteChampions().map {
                ChampionListState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ChampionListState(),
            )
            favoriteScreenState = FavoriteScreenState.Success
        } catch (e: IOException) {
            favoriteScreenState = FavoriteScreenState.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LeagueApplication)
                val championRepository = application.container.championRepository
                FavoriteScreenViewModel(
                    championRepository = championRepository,
                )
            }
        }
    }
}