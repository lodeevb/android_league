package com.example.leagueapp.data.database.champion

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leagueapp.model.ChampionMin


@Entity(tableName = "champions")
data class dbChampion(
    @PrimaryKey
    val id: String,
    val name: String,
    var isFavorite: Boolean = false,
)

fun dbChampion.asDomainTask(): ChampionMin {
    return ChampionMin(
        this.id,
        this.name,
    )
}

fun ChampionMin.asDbChampion(): dbChampion {
    return dbChampion(
        id = this.id,
        name = this.name,
    )
}

fun List<dbChampion>.asDomainObjects(): List<ChampionMin> {
    var championList = this.map {
        ChampionMin(it.id, it.name)
    }
    return championList
}