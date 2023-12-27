package com.example.leagueapp.data

import android.content.Context
import com.example.leagueapp.data.database.ChampionDb
import com.example.leagueapp.network.services.ChampionApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

interface AppContainer {
    val championRepository: ChampionRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl  = "https://ddragon.leagueoflegends.com/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ChampionApiService by lazy {
        retrofit.create(ChampionApiService::class.java)
    }

    override val championRepository: ChampionRepository by lazy {
        CachingChampionRepository(ChampionDb.getDatabase(context = context).ChampionDao(), retrofitService)
    }
}