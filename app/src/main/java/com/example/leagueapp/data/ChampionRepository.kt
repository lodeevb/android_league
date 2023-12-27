package com.example.leagueapp.data

import android.util.Log
import com.example.leagueapp.data.database.ChampionDao
import com.example.leagueapp.data.database.asDomainTasks
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.network.services.ChampionApiService
import com.example.leagueapp.network.services.getChampionsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface ChampionRepository {
    fun getChampions(): Flow<List<ChampionMin>>

    suspend fun refresh()
}

class CachingChampionRepository(private val championDao: ChampionDao, private val championApiService: ChampionApiService) : ChampionRepository {

    override fun getChampions(): Flow<List<ChampionMin>> {
            return championDao.getAllChampions().map {
                it.asDomainTasks()
            }.onEach {
                if (it.isEmpty()) {
                    refresh()
                }
            }
    }

    override suspend fun refresh() {
        try {
            championApiService.getChampionsAsFlow().collect {
                    value ->
                for (task in value) {
                    Log.i("TEST", "refresh: $value")
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}