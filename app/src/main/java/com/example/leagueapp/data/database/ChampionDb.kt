package com.example.leagueapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [dbChampion::class], version = 2, exportSchema = false)
abstract class ChampionDb: RoomDatabase() {
    abstract fun ChampionDao(): ChampionDao

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