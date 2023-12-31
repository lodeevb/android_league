package com.example.leagueapp.data.database.championWithSpells

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ChampionWithSpellsDao {
    @Transaction
    @Query("SELECT * FROM championDetails")
    fun getChampionsWithSpells(): List<ChampionWithSpells>
}