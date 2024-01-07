package com.example.leagueapp.data.database.champion

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leagueapp.model.ChampionMin

/**
 * Represents the 'champions' table in the local database.
 *
 *  @property id Unique identifier for the champion.
 *  @property name Champion's name.
 *  @property isFavorite Boolean if the champion is favorite (default value: false)
 */
@Entity(tableName = "champions")
data class dbChampion(
    @PrimaryKey
    val id: String,
    val name: String,
    var isFavorite: Boolean = false,
)

/**
 * Converts a [ChampionMin] object to its equivalent [dbChampion] representation.
 *
 * @return The converted [dbChampion] object.
 */
fun ChampionMin.asDbChampion(): dbChampion {
    return dbChampion(
        id = this.id,
        name = this.name,
        isFavorite = this.isFavorite
    )
}

/**
 * Converts a list of [dbChampion] objects to their equivalent [ChampionMin] representations.
 *
 * @return The list of converted [ChampionMin] objects.
 */
fun List<dbChampion>.asDomainObjects(): List<ChampionMin> {
    val championList = this.map {
        ChampionMin(it.id, it.name, it.isFavorite)
    }
    return championList
}

/**
 * Converts a [dbChampion] object to its equivalent [ChampionMin] representation.
 *
 * @return The converted [ChampionMin] object.
 */
fun dbChampion.asDomainObject(): ChampionMin {
    return ChampionMin(this.id, this.name, this.isFavorite)
}