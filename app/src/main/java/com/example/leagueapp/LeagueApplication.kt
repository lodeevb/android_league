package com.example.leagueapp

import android.app.Application
import com.example.leagueapp.data.AppContainer
import com.example.leagueapp.data.DefaultAppContainer

/**
 * Custom application class for the LeagueApp.
 * Initializes the AppContainer for dependency injection.
 */
class LeagueApplication : Application() {

    /** The dependency injection container for the application. */
    lateinit var container: AppContainer

    /**
     * Called when the application is starting.
     * This is called before any other application components are created.
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}