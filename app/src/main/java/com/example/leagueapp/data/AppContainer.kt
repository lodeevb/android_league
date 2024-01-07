package com.example.leagueapp.data

import android.content.Context
import com.example.leagueapp.data.database.ChampionDb
import com.example.leagueapp.network.services.ChampionApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * AppContainer defines the interface for providing the [ChampionRepository] and serves as a dependency injection contract.
 */
interface AppContainer {
    val championRepository: ChampionRepository
}

/**
 * DefaultAppContainer is an implementation of [AppContainer].
 * It provides access to the [ChampionRepository] that handles champion-related data retrieval.
 * This implementation uses a combination of local databases and a [Retrofit] service to manage champion data.
 *
 * @param context The Android [Context] used to access the application's resources.
 */
class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl  = "https://ddragon.leagueoflegends.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService: ChampionApiService by lazy {
        retrofit.create(ChampionApiService::class.java)
    }

    /**
     * Lazily initializes and provides access to the [ChampionRepository].
     * The repository manages champion-related data using local databases and a remote API.
     */

    override val championRepository: ChampionRepository by lazy {
        val championDb = ChampionDb.getDatabase(context = context)
        val championDao = championDb.ChampionDao()
        val championDetailDao = championDb.ChampionDetailDao()
        val spellDao = championDb.SpellDao()
        val championWithSpellsDao = championDb.ChampionWithSpellsDao()
        CachingChampionRepository(championDao, championDetailDao, spellDao, championWithSpellsDao, retrofitService)
    }
}