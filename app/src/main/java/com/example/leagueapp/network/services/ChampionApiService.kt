package com.example.leagueapp.network.services

import com.example.leagueapp.network.items.ChampionItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface ChampionApiService {
    @GET("cdn/13.24.1/data/en_US/champion.json")
    suspend fun getChampionData(): List<ChampionItem>
}

fun ChampionApiService.getChampionsAsFlow(): Flow<List<ChampionItem>> = flow {
    emit(getChampionData())
}