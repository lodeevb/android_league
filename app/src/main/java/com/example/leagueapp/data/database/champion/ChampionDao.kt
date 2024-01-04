package com.example.leagueapp.data.database.champion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbChampion)

    @Query("SELECT * FROM champions")
    fun getAllChampions(): Flow<List<dbChampion>>

    @Query("SELECT * FROM champions WHERE isFavorite = true")
    fun getFavoriteChampions(): Flow<List<dbChampion>>

    @Query("SELECT * FROM champions WHERE id = :championId")
    fun getChampionById(championId: String): dbChampion

    @Update
    suspend fun changeFavorite(champion: dbChampion)

}