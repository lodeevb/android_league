package com.example.leagueapp.daotests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.leagueapp.data.database.ChampionDb
import com.example.leagueapp.data.database.championWithSpells.ChampionWithSpellsDao
import com.example.leagueapp.data.database.championdetail.ChampionDetailDao
import com.example.leagueapp.data.database.championdetail.asDbChampionDetail
import com.example.leagueapp.data.database.spell.SpellDao
import com.example.leagueapp.data.database.spell.asDbSpell
import com.example.leagueapp.model.ChampionDetail
import com.example.leagueapp.model.Spell
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ChampionDetailWithSpellsDaoTest {
    private lateinit var championWithSpellsDao: ChampionWithSpellsDao
    private lateinit var spellDao: SpellDao
    private lateinit var championDetailDao: ChampionDetailDao
    private lateinit var championDb: ChampionDb

    private var fakeChampionDetail = ChampionDetail("Lode", "Lode", "The one and only", "Lode was born on August 10th 2001")
    private var fakeSpells = listOf(
        Spell("Lode", "LodeQ", "Gravity Surge", "Unleashes a gravitational surge, pulling nearby enemies toward the champion and briefly slowing their movement speed."),
        Spell("Lode", "LodeE", "Quantum Shift", "Temporarily shifts the champion's presence through quantum dimensions, evading the next incoming enemy ability and granting a brief movement speed boost."),
        Spell("Lode", "LodeW", "Chrono Barrier", "Manipulates time, creating a protective barrier that absorbs incoming damage for a short duration before fading away."),
        Spell("Lode", "LodeR", "Stellar Collapse", "Summons cosmic forces to converge at a target location, causing a massive celestial explosion that damages and stuns enemies caught in the blast radius.")
    )

    private suspend fun addToDatabase() {
        championDetailDao.insert(fakeChampionDetail.asDbChampionDetail())
        fakeSpells.forEach { spell ->
            spellDao.insert(spell.asDbSpell())
        }
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        championDb = Room.inMemoryDatabaseBuilder(context, ChampionDb::class.java)
            .allowMainThreadQueries()
            .build()
        championDetailDao = championDb.ChampionDetailDao()
        spellDao = championDb.SpellDao()
        championWithSpellsDao = championDb.ChampionWithSpellsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        championDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoGetChampionDetailWithSpells_returnDetailAndSpells() = runBlocking {
        addToDatabase()
        val champion = championWithSpellsDao.getChampionsWithSpells(fakeChampionDetail.id).first()
        val spells = champion.spells
        val championDetail = champion.championDetail
        assertEquals(spells.count(), fakeSpells.count())
        assertEquals(championDetail, fakeChampionDetail)
    }


}