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

/**
 * The ChampionRepository interface defines methods for retrieving and manipulating champion-related data.
 * It provides access to champion lists, details, favorites, and methods for refreshing and modifying data.
 */
interface ChampionRepository {

    /**
     * Retrieves a flow of all champions.
     *
     * @return A flow emitting a list of [ChampionMin].
     */
    fun getChampions(): Flow<List<ChampionMin>>

    /**
     * Retrieves a flow of all the favorite champions.
     *
     * @return A flow emitting a list of favorite [ChampionMin].
     */
    fun getFavoriteChampions(): Flow<List<ChampionMin>>

    /**
     * Retrieves a flow of [ChampionWithSpells].
     *
     * @param championId The ID of the champion.
     * @return A flow emitting a [ChampionWithSpells].
     */
    fun getChampionDetail(championId: String): Flow<ChampionWithSpells>

    /**
     * Toggles the favorite status of a champion.
     *
     * @param championId The ID of the champion.
     */
    suspend fun changeFavorite(championId: String)

    /**
     * Inserts a new champion.
     *
     * @param champion The [ChampionMin] to be inserted.
     */
    suspend fun insertChampion(champion: ChampionMin)

    /**
     * Inserts a new champion detail.
     *
     * @param championDetail The [ChampionDetail] to be inserted.
     */
    suspend fun insertChampionDetail(championDetail: ChampionDetail)

    /**
     * Inserts a new spell.
     *
     * @param spell The [Spell] to be inserted.
     */
    suspend fun insertSpell(spell: Spell)

    /**
     * Refreshes the champion data from the API.
     */
    suspend fun refresh()

    /**
     * Refreshes the champion detail data from the API.
     *
     * @param championId The ID of the champion to refresh details for.
     */
    suspend fun refreshDetails(championId: String)

}

/**
 * The CachingChampionRepository class implements the [ChampionRepository] interface
 * to handle champion-related data operations with local caching and API interaction.
 *
 * @property championDao The [ChampionDao] for champion data.
 * @property championDetailDao The [ChampionDetailDao] for champion detail data.
 * @property spellDao The [SpellDao] for spell data.
 * @property championWithSpellsDao The [ChampionWithSpellsDao] for champion data with spells.
 * @property championApiService The [ChampionApiService] responsible for API interactions.
 */
class CachingChampionRepository(private val championDao: ChampionDao, private val championDetailDao: ChampionDetailDao, private val spellDao: SpellDao,
                                private val championWithSpellsDao: ChampionWithSpellsDao, val championApiService: ChampionApiService) : ChampionRepository {

    /**
     * Retrieves the list of all champions as a flow of [ChampionMin].
     * If the local database is empty, triggers a data refresh via [refresh].
     *
     * @return A flow emitting a list of [ChampionMin].
     */
    override fun getChampions(): Flow<List<ChampionMin>> {
            return championDao.getAllChampions().map {
                it.asDomainObjects()
            }.onEach {
                if (it.isEmpty()) {
                    refresh()
                }
            }
    }

    /**
     * Retrieves the list of all favorite champions as a flow of [ChampionMin].
     *
     * @return A flow emitting a list of favorite [ChampionMin].
     */
    override fun getFavoriteChampions(): Flow<List<ChampionMin>> {
        return championDao.getFavoriteChampions().map {
            it.asDomainObjects()
        }
    }

    /**
     * Retrieves detailed information about a specific champion by championId as a flow of [ChampionWithSpells].
     *
     * @param championId The championId of the champion to be retrieved.
     * @return A flow emitting a list of [ChampionWithSpells].
     */
    override fun getChampionDetail(championId: String): Flow<ChampionWithSpells> {
        return championWithSpellsDao.getChampionsWithSpells(championId).map {
            it
        }
    }

    /**
     * Toggles the 'favorite' status of a champion identified by championId.
     *
     * @param championId The championId of the champion to be retrieved.
     */
    override suspend fun changeFavorite(championId: String) {
        val champion = championDao.getChampionById(championId)
        champion.apply {
            isFavorite = !isFavorite
        }
        championDao.changeFavorite(champion)
    }

    /**
     * Inserts a new [ChampionMin] into the local database.
     *
     * @param champion The [ChampionMin] to be inserted.
     */
    override suspend fun insertChampion(champion: ChampionMin) {
        championDao.insert(champion.asDbChampion())
    }

    /**
     * Inserts a new [ChampionDetail] into the local database.
     *
     * @param championDetail The [ChampionDetail] to be inserted.
     */
    override suspend fun insertChampionDetail(championDetail: ChampionDetail) {
        championDetailDao.insert(championDetail.asDbChampionDetail())
    }

    /**
     * Inserts a new [Spell] into the local database.
     *
     * @param spell The [Spell] to be inserted.
     */
    override suspend fun insertSpell(spell: Spell) {
        spellDao.insert(spell.asDbSpell())
    }


    /**
     * Refreshes the list of champions by fetching data from the remote API and inserting it into the local database.
     * If the local database is empty, fetches the champion data from the API and inserts it.
     */
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

    /**
     * Refreshes the details of a specific champion by fetching the champion details and spells from the remote API.
     *
     * @param championId The championId of the champion to fetch the details for
     */
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

