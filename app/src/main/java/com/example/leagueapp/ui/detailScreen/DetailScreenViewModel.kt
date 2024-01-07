package com.example.leagueapp.ui.detailScreen

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
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpells
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing data for the [DetailScreen].
 *
 * @property championRepository The [repository][ChampionRepository] for retrieving champion data.
 */
class DetailScreenViewModel(private val championRepository: ChampionRepository) : ViewModel() {

    /** The state of DetailScreen */
    var detailScreenState: DetailScreenState by mutableStateOf(DetailScreenState.Loading)
        private set

    /** The state of ChampionDetail */
    private val _detailChampionState = MutableStateFlow(ChampionDetailState())
    val detailChampionState: StateFlow<ChampionDetailState> = _detailChampionState


    /**
     * Fetches details of a champion using the champion ID.
     *
     * @param championId The ID of the champion to fetch details for.
     */
    fun fetchChampionDetails(championId: String) {
            var championDetail: ChampionWithSpells? = null
            viewModelScope.launch {
                try {
                    championRepository.refreshDetails(championId)
                    championDetail = championRepository.getChampionDetail(championId).first()
                    _detailChampionState.value = ChampionDetailState(championDetail!!)
                    detailScreenState = DetailScreenState.Success
                }
                catch (e: Exception) {
                    detailScreenState = DetailScreenState.Error
                }
            }
    }

    /**
     * Updates the favorite status of a champion using the champion ID.
     *
     * @param championId The ID of the champion to update the favorite status for.
     */
    fun updateFavorite(championId: String) {
        viewModelScope.launch {
            championRepository.changeFavorite(championId)
        }
    }

    /**
     * Companion object providing a [ViewModelProvider.Factory] for creating instances of [DetailScreenViewModel].
     */
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