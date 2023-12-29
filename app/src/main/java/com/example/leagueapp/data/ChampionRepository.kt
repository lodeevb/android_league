package com.example.leagueapp.data

import android.util.Log
import com.example.leagueapp.data.database.ChampionDao
import com.example.leagueapp.data.database.ChampionDetailDao
import com.example.leagueapp.data.database.asDbChampion
import com.example.leagueapp.data.database.asDbChampionDetail
import com.example.leagueapp.data.database.asDomainObject
import com.example.leagueapp.data.database.asDomainObjects
import com.example.leagueapp.model.ChampionDetail
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.network.asDomainObject
import com.example.leagueapp.network.services.ChampionApiService
import com.example.leagueapp.network.services.getChampionDetailsAsFlow
import com.example.leagueapp.network.services.getChampionsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface ChampionRepository {
    fun getChampions(): Flow<List<ChampionMin>>

    fun getChampionDetail(championId: String): Flow<ChampionDetail>

    suspend fun insertChampion(champion: ChampionMin)

    suspend fun insertChampionDetail(championDetail: ChampionDetail)

    suspend fun refresh()

    suspend fun refreshDetails(championId: String)
}

class CachingChampionRepository(private val championDao: ChampionDao, private val championDetailDao:  ChampionDetailDao, private val championApiService: ChampionApiService) : ChampionRepository {

    override fun getChampions(): Flow<List<ChampionMin>> {
            return championDao.getAllChampions().map {
                it.asDomainObjects()
            }.onEach {
                if (it.isEmpty()) {
                    refresh()
                }
            }
    }

    override fun getChampionDetail(championId: String): Flow<ChampionDetail> {
        return championDetailDao.getChampionDetails(championId).map {
            it.asDomainObject()
        }
    }

    override suspend fun insertChampion(champion: ChampionMin) {
        championDao.insert(champion.asDbChampion())
    }

    override suspend fun insertChampionDetail(championDetail: ChampionDetail) {
        championDetailDao.insert(championDetail.asDbChampionDetail())
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

    override suspend fun refreshDetails(championId: String) {
        try {
            championApiService.getChampionDetailsAsFlow(championId).collect {
                    championDetail ->
                    Log.i("DetailTest", "refreshDetail: $championDetail")
                    insertChampionDetail(championDetail.asDomainObject())
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}

