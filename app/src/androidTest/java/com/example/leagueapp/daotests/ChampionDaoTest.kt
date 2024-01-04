package com.example.leagueapp.daotests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.leagueapp.data.database.ChampionDb
import com.example.leagueapp.data.database.champion.ChampionDao
import com.example.leagueapp.data.database.champion.asDbChampion
import com.example.leagueapp.data.database.champion.asDomainObject
import com.example.leagueapp.model.ChampionMin
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ChampionDaoTest {
    private lateinit var championDao: ChampionDao
    private lateinit var championDb: ChampionDb

    private var fakeUnfavChampion = ChampionMin("Lode", "Lode")
    private var fakeFavChampion = ChampionMin("Jeanke", "Jeanke", true)

    private suspend fun addOne(){
        championDao.insert(fakeUnfavChampion.asDbChampion())
    }

    private suspend fun addMultiple(){
        championDao.insert(fakeUnfavChampion.asDbChampion())
        championDao.insert(fakeFavChampion.asDbChampion())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()

        championDb = Room.inMemoryDatabaseBuilder(context, ChampionDb::class.java)
            .allowMainThreadQueries()
            .build()
        championDao = championDb.ChampionDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        championDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertChampionIntoDb() = runBlocking {
        addOne()
        val allChampions = championDao.getAllChampions().first()
        assertEquals(allChampions[0].asDomainObject(), fakeUnfavChampion)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllChampions_returnsAll() = runBlocking {
        addMultiple()
        val allChampions = championDao.getAllChampions().first()
        assertEquals(allChampions[0].asDomainObject(), fakeUnfavChampion)
        assertEquals(allChampions[1].asDomainObject(), fakeFavChampion)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetFavoriteChamps_returnsFavorites() = runBlocking {
        addMultiple()
        val favoriteChampions = championDao.getFavoriteChampions().first()
        assertEquals(favoriteChampions[0].asDomainObject(), fakeFavChampion)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetChampionById_returnsChampion() = runBlocking {
        addMultiple()
        val championById = championDao.getChampionById(fakeUnfavChampion.id)
        assertEquals(championById.asDomainObject(),fakeUnfavChampion)
    }

    @Test
    @Throws(Exception::class)
    fun daoChangeFavorite_changedFavorite() = runBlocking {
        addMultiple()
        val favorite = fakeUnfavChampion.isFavorite
        val dbChamp = fakeUnfavChampion.asDbChampion()
        dbChamp.apply {
            isFavorite = !isFavorite
        }
        championDao.changeFavorite(dbChamp)
        val champion = championDao.getChampionById(fakeUnfavChampion.id)
        assertTrue(favorite != champion.asDomainObject().isFavorite)
    }

}