package com.example.leagueapp.data.database.spell

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface SpellDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: dbSpell)

/*    @Query("SELECT * FROM spells")
    fun getChampionSpells(championId: String): Flow<List<dbSpell>>*/
}