package com.example.leagueapp.network

import com.example.leagueapp.data.database.spell.dbSpell
import com.example.leagueapp.model.Spell
import kotlinx.serialization.Serializable

@Serializable
data class SpellApi(
    val id: String,
    val name: String,
    val description: String
)

fun SpellApi.asDbSpell(championId: String): dbSpell {
    return dbSpell(
        championId = championId,
        id = this.id,
        name = this.name,
        description = this.description
    )
}

fun SpellApi.asDomainObject(championId: String) : Spell {
    return Spell(championId, this.id, this.name, this.description)
}