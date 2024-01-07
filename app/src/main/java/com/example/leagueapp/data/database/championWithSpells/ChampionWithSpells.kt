package com.example.leagueapp.data.database.championWithSpells

import androidx.room.Embedded
import androidx.room.Relation
import com.example.leagueapp.data.database.spell.dbSpell
import com.example.leagueapp.model.ChampionDetail

/**
 * Represents a combined entity class containing [ChampionDetail] and a list of associated spells.
 *
 * @property championDetail The [ChampionDetail] entity embedded within this object.
 * @property spells A list of [dbSpell] entities associated with the champion.
 */
data class ChampionWithSpells(
    @Embedded
    val championDetail: ChampionDetail,

    @Relation(
        parentColumn = "id",
        entityColumn = "championId"
    )
    val spells: List<dbSpell>
)