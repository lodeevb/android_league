package com.example.leagueapp

import com.example.leagueapp.network.ChampionApi
import com.example.leagueapp.network.items.ChampionData
import com.example.leagueapp.network.services.ChampionApiService
import com.example.leagueapp.network.services.getChampionsAsFlow
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class ChampionApiServiceTest {

    @Test
    fun getChampionsAsFlow_Success() = runTest {
        val mockChampion1 = ChampionApi("Lode", "Lode",true)
        val mockChampion2 = ChampionApi("Henk", "Henk", false)

        val championData = ChampionData(
            mapOf(
                "1" to mockChampion1,
                "2" to mockChampion2
            )
        )

        val mockApiService = mock<ChampionApiService> {
            onBlocking { getChampionData() }.thenReturn(championData)
        }

        val result = mockApiService.getChampionsAsFlow().toList().first()

        assertEquals(2, result.size)
        assertEquals("Lode",result[0].name)
        assertEquals("Henk",result[1].name)

        verify(mockApiService).getChampionData()
    }
}