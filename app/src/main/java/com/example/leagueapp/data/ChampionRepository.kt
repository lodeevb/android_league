package com.example.leagueapp.data

import android.util.Log
import com.example.leagueapp.data.database.champion.ChampionDao
import com.example.leagueapp.data.database.champion.asDbChampion
import com.example.leagueapp.data.database.champion.asDomainObjects
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpells
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpellsDao
import com.example.leagueapp.data.database.championdetail.ChampionDetailDao
import com.example.leagueapp.data.database.championdetail.asDbChampionDetail
import com.example.leagueapp.data.database.spell.SpellDao
import com.example.leagueapp.data.database.spell.asDbSpell
import com.example.leagueapp.model.ChampionDetail
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.model.Spell
import com.example.leagueapp.network.asDomainObject
import com.example.leagueapp.network.services.ChampionApiService
import com.example.leagueapp.network.services.getChampionDetailsAsFlow
import com.example.leagueapp.network.services.getChampionsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

interface ChampionRepository {
    fun getChampions(): Flow<List<ChampionMin>>

    fun getFavoriteChampions(): Flow<List<ChampionMin>>

    fun getChampionDetail(championId: String): Flow<ChampionWithSpells>

    suspend fun changeFavorite(championId: String)

    suspend fun insertChampion(champion: ChampionMin)

    suspend fun insertChampionDetail(championDetail: ChampionDetail)

    suspend fun insertSpell(spell: Spell)

    suspend fun refresh()

    suspend fun refreshDetails(championId: String)

}

class CachingChampionRepository(private val championDao: ChampionDao, private val championDetailDao: ChampionDetailDao, private val spellDao: SpellDao,
                                private val championWithSpellsDao: ChampionWithSpellsDao, val championApiService: ChampionApiService) : ChampionRepository {

    override fun getChampions(): Flow<List<ChampionMin>> {
            return championDao.getAllChampions().map {
                it.asDomainObjects()
            }.onEach {
                if (it.isEmpty()) {
                    refresh()
                }
            }
    }

    override fun getFavoriteChampions(): Flow<List<ChampionMin>> {
        return championDao.getFavoriteChampions().map {
            it.asDomainObjects()
        }
    }

    override fun getChampionDetail(championId: String): Flow<ChampionWithSpells> {
        return championWithSpellsDao.getChampionsWithSpells(championId).map {
            it
        }
    }

    override suspend fun changeFavorite(championId: String) {
        val champion = championDao.getChampionById(championId)
        champion.apply {
            isFavorite = !isFavorite
        }
        championDao.changeFavorite(champion)
    }

    override suspend fun insertChampion(champion: ChampionMin) {
        championDao.insert(champion.asDbChampion())
    }

    override suspend fun insertChampionDetail(championDetail: ChampionDetail) {
        championDetailDao.insert(championDetail.asDbChampionDetail())
    }

    override suspend fun insertSpell(spell: Spell) {
        spellDao.insert(spell.asDbSpell())
    }


    override suspend fun refresh() {
        try {
            championApiService.getChampionsAsFlow().collect {
                    champions ->
                for (champion in champions) {
                    Log.i("Champion", "ChampionRefresh: $champion")
                    insertChampion(champion.asDomainObject())
                }
            }

        } catch (e: SocketTimeoutException) {
            // log something
        }
    }

    override suspend fun refreshDetails(championId: String) {
        try {
            championApiService.getChampionDetailsAsFlow(championId).collect {
                    championDetail ->
                    Log.i("ChampionDetail", "ChampionDetailRefresh: $championDetail")
                    insertChampionDetail(championDetail.asDomainObject())
                for (spell in championDetail.spells) {
                    Log.i("Spell", "SpellRefresh: $spell")
                    insertSpell(spell.asDomainObject(championId))
                }
            }
        } catch (e: SocketTimeoutException) {
            // log something
        }
    }
}

