package com.example.leagueapp

import android.app.Application
import com.example.leagueapp.data.AppContainer
import com.example.leagueapp.data.DefaultAppContainer

class LeagueApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}