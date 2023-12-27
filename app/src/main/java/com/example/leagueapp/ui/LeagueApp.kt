package com.example.leagueapp.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.ui.homeScreen.HomeScreen

//Dummy List
val championList = listOf<ChampionMin>(
    ChampionMin("aatrox", "Aatrox"),
    ChampionMin("aatrox", "Aatrox"),
    ChampionMin("aatrox", "Aatrox"),
    ChampionMin("aatrox", "Aatrox"),
    ChampionMin("aatrox", "Aatrox"),
    ChampionMin("aatrox", "Aatrox"),
    ChampionMin("aatrox", "Aatrox"),
)

@Composable
fun LeagueApp(navController: NavHostController = rememberNavController()) {
    Scaffold { innerPadding ->
        NavHost(navController = navController, startDestination = "home") {
            composable(route = "home") {
                HomeScreen(champions = championList, innerPadding = innerPadding)
            }
        }
    }
}