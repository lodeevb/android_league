package com.example.leagueapp.data.database.championWithSpells

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionWithSpellsDao {
    @Transaction
    @Query("SELECT * FROM championDetails WHERE id = :championId")
    fun getChampionsWithSpells(championId: String): Flow<ChampionWithSpells>
}