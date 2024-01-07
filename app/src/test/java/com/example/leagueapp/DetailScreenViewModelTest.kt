package com.example.leagueapp

import com.example.leagueapp.data.ChampionRepository
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpells
import com.example.leagueapp.data.database.spell.dbSpell
import com.example.leagueapp.model.ChampionDetail
import com.example.leagueapp.network.ChampionDetailApi
import com.example.leagueapp.network.SpellApi
import com.example.leagueapp.network.asDomainObject
import com.example.leagueapp.ui.detailScreen.DetailScreenState
import com.example.leagueapp.ui.detailScreen.DetailScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailScreenViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Mock
    private lateinit var repository: ChampionRepository

    private lateinit var viewModel: DetailScreenViewModel

    @Before
    fun setUp() {
        viewModel = DetailScreenViewModel(repository)
    }

    private val emptyDetail = ChampionDetailApi("", "", "", "", listOf(SpellApi("", "", ""))).asDomainObject()
    private val lodeDetail = ChampionDetailApi("Lode", "Lode", "Lode", "Lode", listOf(SpellApi("Lode", "Lode", "Lode"))).asDomainObject()

    @Test
    fun championDetailApiState_defaultStateLoading() {
        assert(viewModel.detailScreenState is DetailScreenState.Loading)
    }

    @Test
    fun fetchChampionDetails_successResponse() = runTest {
        val championId = "Lode"
        val championWithSpells = ChampionWithSpells(ChampionDetail("Lode", "Lode", "Lode", "Lode"), listOf(
            dbSpell("Lode", "Lodeq", "Lode","Lode")
        ))

        Mockito.`when`(repository.refreshDetails(championId)).thenReturn(Unit)
        Mockito.`when`(repository.getChampionDetail(championId)).thenReturn(flowOf(championWithSpells))
        viewModel.fetchChampionDetails(championId)
        assertEquals(DetailScreenState.Success, viewModel.detailScreenState)
        assertEquals(championWithSpells, viewModel.detailChampionState.value.champion)
    }

    @Test
    fun fetchChampionDetails_errorResponse() = runTest {
        val championId = "Lode"
        viewModel.fetchChampionDetails(championId)
        assertEquals(DetailScreenState.Error, viewModel.detailScreenState)
    }

    @Test
    fun updateFavorite_callsChangeFavorite() = runTest {
        val championId = "Lode"
        viewModel.updateFavorite(championId)
        Mockito.verify(repository).changeFavorite(championId)
    }

}