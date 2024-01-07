package com.example.leagueapp.data.database.spell

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Data Access Object (DAO) interface for handling operations related to Spell entities in the local database.
 */
@Dao
interface SpellDao {

    /**
     * Inserts a spell into the database. If a conflict occurs, it replaces the existing spell data.
     *
     * @param spell The [dbSpell] that has to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spell: dbSpell)
}