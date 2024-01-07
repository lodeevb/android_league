package com.example.leagueapp.data.database.champion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for Champions.
 */
@Dao
interface ChampionDao {
    /**
     * Inserts a [dbChampion] object into the database.
     *
     * @param item The [dbChampion] object to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: dbChampion)

    /**
     * Retrieves all champions from the 'champions' table.
     *
     * @return A flow emitting a list of [dbChampion]
     */
    @Query("SELECT * FROM champions")
    fun getAllChampions(): Flow<List<dbChampion>>

    /**
     * Retrieves favorite champions from the 'champions' table.
     *
     * @return A flow emitting a list of favorite [dbChampion]
     */
    @Query("SELECT * FROM champions WHERE isFavorite = true")
    fun getFavoriteChampions(): Flow<List<dbChampion>>

    /**
     * Retrieves a champion by its ID from the 'champions' table.
     *
     * @param championId The ID of the champion to retrieve.
     * @return The corresponding [dbChampion] object.
     */
    @Query("SELECT * FROM champions WHERE id = :championId")
    suspend fun getChampionById(championId: String): dbChampion

    /**
     * Updates the favorite status of a champion in the 'champions' table.
     *
     * @param champion The [dbChampion] object to update.
     */
    @Update
    suspend fun changeFavorite(champion: dbChampion)

}