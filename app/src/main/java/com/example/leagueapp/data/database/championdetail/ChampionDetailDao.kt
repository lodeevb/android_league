package com.example.leagueapp.data.database.championdetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbChampionDetail)

    @Query("SELECT * FROM championDetails WHERE id = :championId")
    fun getChampionDetails(championId: String): Flow<dbChampionDetail>

}