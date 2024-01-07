package com.example.leagueapp.data.database.championWithSpells

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for handling operations related to [ChampionWithSpells] entities in the local database.
 */
@Dao
interface ChampionWithSpellsDao {

    /**
     * Retrieves a champion along with its spells based on the provided championId.
     *
     * @param championId The unique identifier of the champion to retrieve.
     * @return A flow emitting the [ChampionWithSpells] entity associated with the given championId.
     */
    @Transaction
    @Query("SELECT * FROM championDetails WHERE id = :championId")
    fun getChampionsWithSpells(championId: String): Flow<ChampionWithSpells>
}