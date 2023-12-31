package com.example.leagueapp.data.database.championWithSpells

import androidx.room.Embedded
import androidx.room.Relation
import com.example.leagueapp.data.database.spell.dbSpell
import com.example.leagueapp.model.ChampionDetail

data class ChampionWithSpells(
    @Embedded
    val championDetail: ChampionDetail,

    @Relation(
        parentColumn = "id",
        entityColumn = "championId"
    )
    val spells: List<dbSpell>
)