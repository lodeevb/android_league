package com.example.leagueapp.data

import android.util.Log
import com.example.leagueapp.data.database.ChampionDao
import com.example.leagueapp.data.database.ChampionDetailDao
import com.example.leagueapp.data.database.ChampionDetailDb
import com.example.leagueapp.data.database.asDbChampion
import com.example.leagueapp.data.database.asDomainObject
import com.example.leagueapp.data.database.asDomainObjects
import com.example.leagueapp.model.ChampionDetail
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.network.asDomainObject
import com.example.leagueapp.network.services.ChampionApiService
import com.example.leagueapp.network.services.getChampionsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface ChampionRepository {
    fun getChampions(): Flow<List<ChampionMin>>

    fun getChampionDetail(championid: String): Flow<ChampionDetail>

    suspend fun insertChampion(champion: ChampionMin)

    suspend fun refresh()
}

class CachingChampionRepository(private val championDao: ChampionDao, private val championDetailDao:  ChampionDetailDao, val championApiService: ChampionApiService) : ChampionRepository {

    override fun getChampions(): Flow<List<ChampionMin>> {
            return championDao.getAllChampions().map {
                it.asDomainObjects()
            }.onEach {
                if (it.isEmpty()) {
                    refresh()
                }
            }
    }

    override fun getChampionDetail(championid: String): Flow<ChampionDetail> {
        return championDetailDao.getChampionDetails(championid).map {
            it.asDomainObject()
        }
    }

    override suspend fun insertChampion(champion: ChampionMin) {
        championDao.insert(champion.asDbChampion())
    }


    override suspend fun refresh() {
        try {
            championApiService.getChampionsAsFlow().collect {
                    champions ->
                for (champion in champions) {
                    Log.i("TEST", "refresh: $champion")
                    insertChampion(champion.asDomainObject())
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}

