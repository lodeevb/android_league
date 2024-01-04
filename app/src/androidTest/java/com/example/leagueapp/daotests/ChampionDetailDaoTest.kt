package com.example.leagueapp.daotests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.leagueapp.data.database.ChampionDb
import com.example.leagueapp.data.database.championdetail.ChampionDetailDao
import com.example.leagueapp.data.database.championdetail.asDbChampionDetail
import com.example.leagueapp.data.database.championdetail.asDomainObject
import com.example.leagueapp.model.ChampionDetail
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ChampionDetailDaoTest {
    private lateinit var championDetailDao: ChampionDetailDao
    private lateinit var championDb: ChampionDb

    private var fakeChampionDetail = ChampionDetail("Lode", "Lode", "The one and only", "Lode was born on August 10th 2001")

    private suspend fun addOne(){
        championDetailDao.insert(fakeChampionDetail.asDbChampionDetail())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        championDb = Room.inMemoryDatabaseBuilder(context, ChampionDb::class.java)
            .allowMainThreadQueries()
            .build()
        championDetailDao = championDb.ChampionDetailDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        championDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertChampionDetailIntoDb() = runBlocking {
        addOne()
        val championDetail = championDetailDao.getChampionDetails(fakeChampionDetail.id).first()
        assertEquals(championDetail.asDomainObject(), fakeChampionDetail)
    }
}