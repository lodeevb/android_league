package com.example.leagueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.leagueapp.ui.LeagueApp
import com.example.leagueapp.ui.theme.LeagueAppTheme


/**
 * This activity serves as the main entry point of the LeagueApp.
 * It sets up the UI using Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     * destroyed then this Bundle contains the data it most recently supplied in
     * [onSaveInstanceState]. Note: Otherwise, it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeagueAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LeagueApp()
                }
            }
        }
    }
}