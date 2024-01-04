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

fun ChampionMin.asDbChampion(): dbChampion {
    return dbChampion(
        id = this.id,
        name = this.name,
        isFavorite = this.isFavorite
    )
}

fun List<dbChampion>.asDomainObjects(): List<ChampionMin> {
    var championList = this.map {
        ChampionMin(it.id, it.name, it.isFavorite)
    }
    return championList
}

fun dbChampion.asDomainObject(): ChampionMin {
    return ChampionMin(this.id, this.name, this.isFavorite)
}