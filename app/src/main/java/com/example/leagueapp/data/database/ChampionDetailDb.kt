package com.example.leagueapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [dbChampionDetail::class], version = 2, exportSchema = false)
abstract class ChampionDetailDb: RoomDatabase() {
    abstract fun ChampionDetailDao(): ChampionDetailDao

    companion object {
        @Volatile
        private var Instance: ChampionDetailDb? = null

        fun getDatabase(context: Context): ChampionDetailDb {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ChampionDetailDb::class.java, "champDatabase")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it}
            }
        }
    }
}