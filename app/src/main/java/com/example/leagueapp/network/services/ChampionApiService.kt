package com.example.leagueapp.network.services

import android.util.Log
import com.example.leagueapp.network.ChampionApi
import com.example.leagueapp.network.ChampionDetailApi
import com.example.leagueapp.network.items.ChampionData
import com.example.leagueapp.network.items.ChampionDetailData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service interface defining endpoints related to champion data retrieval from the API.
 */
interface ChampionApiService {

    /**
     * Retrieves the overall champion data from the API.
     *
     * @return [ChampionData] object representing overall champion data.
     */
    @GET("cdn/13.24.1/data/en_US/champion.json")
    suspend fun getChampionData(): ChampionData

    /**
     * Retrieves specific details of a champion based on the provided championId.
     *
     * @param championId ID of the champion to fetch details for.
     * @return [ChampionDetailData] object representing detailed information about the champion.
     */
    @GET("cdn/13.24.1/data/en_US/champion/{championId}.json")
    suspend fun getChampionDetails(@Path("championId") championId: String): ChampionDetailData

}

/**
 * Extension function to fetch the list of champions as a Flow of [ChampionApi].
 *
 * @return A Flow emitting a list of [ChampionApi] objects representing champion data.
 */
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

/**
 * Extension function to fetch champion details as a Flow of [ChampionDetailApi].
 *
 * @param championId ID of the champion to fetch details for.
 * @return A Flow emitting [ChampionDetailApi] object representing detailed information about the champion.
 */
fun ChampionApiService.getChampionDetailsAsFlow(championId: String): Flow<ChampionDetailApi> = flow {
    try {
        val championDetails = getChampionDetails(championId)
        val champDetails = championDetails.data.values.first()
        emit(champDetails)
    }
    catch(e: Exception){
        Log.e("API", "getChampionDetailsAsFlow: " +e.stackTraceToString(), )
    }
}