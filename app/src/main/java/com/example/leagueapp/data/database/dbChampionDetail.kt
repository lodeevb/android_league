package com.example.leagueapp.data.database

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leagueapp.model.ChampionDetail

@Entity(tableName = "championDetails")
data class dbChampionDetail(
    @PrimaryKey
    val id: String,
    val name: String,
    val title: String,
    val lore: String
)

fun ChampionDetail.asDbChampionDetail(): dbChampionDetail {
    return dbChampionDetail(
        id = this.id,
        name = this.name,
        title = this.title,
        lore = this.lore
    )
}

fun dbChampionDetail.asDomainObject(): ChampionDetail {
    return ChampionDetail(
        id = this.id,
        name = this.name,
        title = this.title,
        lore = this.lore
    )
}