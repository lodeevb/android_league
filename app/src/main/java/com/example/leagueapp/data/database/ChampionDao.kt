package com.example.leagueapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbChampion)

    @Query("SELECT * FROM Champions")
    fun getAllChampions(): Flow<List<dbChampion>>

}