package com.example.leagueapp.network.services

import android.util.Log
import com.example.leagueapp.network.ChampionApi
import com.example.leagueapp.network.items.ChampionData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface ChampionApiService {
    @GET("cdn/13.24.1/data/en_US/champion.json")
    suspend fun getChampionData(): ChampionData
}

fun ChampionApiService.getChampionsAsFlow(): Flow<List<ChampionApi>> = flow {
    try {
        val championData = getChampionData()
        val championList = championData.data.values.toList()
        emit(championList)
    }
    catch(e: Exception){
        Log.e("API", "getChampionsAsFlow: "+e.stackTraceToString(), )
    }
}