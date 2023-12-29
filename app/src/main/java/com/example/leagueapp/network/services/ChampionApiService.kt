package com.example.leagueapp.network.services

import android.util.Log
import com.example.leagueapp.network.ChampionApi
import com.example.leagueapp.network.ChampionDetailApi
import com.example.leagueapp.network.items.ChampionData
import com.example.leagueapp.network.items.ChampionDetailData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

interface ChampionApiService {
    @GET("cdn/13.24.1/data/en_US/champion.json")
    suspend fun getChampionData(): ChampionData

    @GET("cdn/13.24.1/data/en_US/champion/{championid}.json")
    suspend fun getChampionDetails(@Path("championid") championid: String): ChampionDetailData

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

fun ChampionApiService.getChampionDetailsAsFlow(championid: String): Flow<ChampionDetailApi> = flow {
    try {
        val championDetails = getChampionDetails(championid)
        val champDetails = championDetails.data.values.first()
        emit(champDetails)
    }
    catch(e: Exception){
        Log.e("API", "getChampionDetailsAsFlow: "+e.stackTraceToString(), )
    }
}