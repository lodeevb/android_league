package com.example.leagueapp.data.database.championdetail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for handling operations related to ChampionDetail entities in the local database.
 */
@Dao
interface ChampionDetailDao {
    /**
     * Inserts or replaces a [dbChampionDetail] item in the 'championDetails' table.
     *
     * @param item The [dbChampionDetail] to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbChampionDetail)

    /**
     * Retrieves champion details from the 'championDetails' table based on the specified championId.
     *
     * @param championId The unique identifier of the champion.
     * @return A flow emitting the [dbChampionDetail] associated with the provided championId.
     */
    @Query("SELECT * FROM championDetails WHERE id = :championId")
    fun getChampionDetails(championId: String): Flow<dbChampionDetail>
}