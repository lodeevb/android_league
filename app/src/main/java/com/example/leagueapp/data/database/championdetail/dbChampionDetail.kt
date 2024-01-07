package com.example.leagueapp.data.database.championdetail

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leagueapp.model.ChampionDetail

/**
 * Represents the 'championDetails' table in the local database.
 *
 * @property id Unique identifier for the champion.
 * @property name Champion's name.
 * @property title Champion's title.
 * @property lore Lore or backstory of the champion.
 */
@Entity(tableName = "championDetails")
data class dbChampionDetail(
    @PrimaryKey
    val id: String,
    val name: String,
    val title: String,
    val lore: String,

)

/**
 * Converts a [ChampionDetail] object into its database equivalent [dbChampionDetail].
 *
 * @return The converted [dbChampionDetail] object.
 */
fun ChampionDetail.asDbChampionDetail(): dbChampionDetail {
    return dbChampionDetail(
        id = this.id,
        name = this.name,
        title = this.title,
        lore = this.lore,
    )
}

/**
 * Converts a [dbChampionDetail] object into its domain model equivalent [ChampionDetail].
 *
 * @return The converted [ChampionDetail] object.
 */
fun dbChampionDetail.asDomainObject(): ChampionDetail {
    return ChampionDetail(
        id = this.id,
        name = this.name,
        title = this.title,
        lore = this.lore
    )
}