package com.example.leagueapp.data.database.spell

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leagueapp.model.Spell

@Entity(tableName = "spells")
data class dbSpell(
    val championId: String,
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
)

fun Spell.asDbSpell(): dbSpell {
    return dbSpell(
        championId = this.championId,
        id = this.id,
        name = this.name,
        description = this.description,
    )
}