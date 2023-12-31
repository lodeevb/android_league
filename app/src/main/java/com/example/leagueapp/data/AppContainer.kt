package com.example.leagueapp.data

import android.content.Context
import com.example.leagueapp.data.database.ChampionDb
import com.example.leagueapp.network.services.ChampionApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val championRepository: ChampionRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl  = "https://ddragon.leagueoflegends.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: ChampionApiService by lazy {
        retrofit.create(ChampionApiService::class.java)
    }

    override val championRepository: ChampionRepository by lazy {
        val championDb = ChampionDb.getDatabase(context = context)
        val championDao = championDb.ChampionDao()
        val championDetailDao = championDb.ChampionDetailDao()
        val spellDao = championDb.SpellDao()
        CachingChampionRepository(championDao, championDetailDao, spellDao, retrofitService)
    }
}