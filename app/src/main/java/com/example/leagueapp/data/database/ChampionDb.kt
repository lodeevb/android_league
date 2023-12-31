package com.example.leagueapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.leagueapp.data.database.champion.ChampionDao
import com.example.leagueapp.data.database.champion.dbChampion
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpellsDao
import com.example.leagueapp.data.database.championdetail.ChampionDetailDao
import com.example.leagueapp.data.database.championdetail.dbChampionDetail
import com.example.leagueapp.data.database.spell.SpellDao
import com.example.leagueapp.data.database.spell.dbSpell

@Database(entities = [dbChampion::class, dbChampionDetail::class, dbSpell::class], version = 5, exportSchema = false)
abstract class ChampionDb: RoomDatabase() {
    abstract fun ChampionDao(): ChampionDao
    abstract fun ChampionDetailDao(): ChampionDetailDao
    abstract fun SpellDao(): SpellDao
    abstract fun ChampionWithSpellsDao(): ChampionWithSpellsDao

    companion object{
        @Volatile
        private var Instance: ChampionDb? = null

        fun getDatabase(context: Context): ChampionDb {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ChampionDb::class.java, "champDatabase")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {Instance = it}
            }
        }
    }
}