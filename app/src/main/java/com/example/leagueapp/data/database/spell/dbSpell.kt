package com.example.leagueapp.data.database.spell

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leagueapp.model.Spell

/**
 * Represents the 'spells' table in the local database.
 *
 *  @property championId Champion identifier for the spells.
 *  @property id Unique identifier for the spell.
 *  @property name Name of the spell.
 *  @property description Description of the spell.
 *
 */
@Entity(tableName = "spells")
data class dbSpell(
    val championId: String,
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
)

/**
 * Maps a [Spell] object to its corresponding [dbSpell] entity for database operations.
 *
 * @return Converted [Spell] object to [dbSpell]
 */
fun Spell.asDbSpell(): dbSpell {
    return dbSpell(
        championId = this.championId,
        id = this.id,
        name = this.name,
        description = this.description,
    )
}