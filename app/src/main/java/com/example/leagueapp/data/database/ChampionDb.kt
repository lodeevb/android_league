package com.example.leagueapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.leagueapp.data.database.ChampionDb.Companion.Instance
import com.example.leagueapp.data.database.champion.ChampionDao
import com.example.leagueapp.data.database.champion.dbChampion
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpellsDao
import com.example.leagueapp.data.database.championdetail.ChampionDetailDao
import com.example.leagueapp.data.database.championdetail.dbChampionDetail
import com.example.leagueapp.data.database.spell.SpellDao
import com.example.leagueapp.data.database.spell.dbSpell

/**
 * The ChampionDb class represents the [Room] database for champion-related data storage.
 * It manages entities such as [dbChampion], [dbChampionDetail], and [dbSpell].
 * This database serves as a local cache to store champion information and related details.
 *
 * @property Instance Volatile database instance to ensure its visibility across threads.
 */
@Database(entities = [dbChampion::class, dbChampionDetail::class, dbSpell::class], version = 5, exportSchema = false)
abstract class ChampionDb: RoomDatabase() {
    abstract fun ChampionDao(): ChampionDao
    abstract fun ChampionDetailDao(): ChampionDetailDao
    abstract fun SpellDao(): SpellDao
    abstract fun ChampionWithSpellsDao(): ChampionWithSpellsDao

    companion object{
        @Volatile
        private var Instance: ChampionDb? = null

        /**
         * Provides a singleton instance of the [ChampionDb].
         * If an instance already exists, it returns the existing instance; otherwise, it creates a new one.
         *
         * @param context The Android [Context] used to access the application's resources.
         * @return The singleton instance of [ChampionDb].
         */
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