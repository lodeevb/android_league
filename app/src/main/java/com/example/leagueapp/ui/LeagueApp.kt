package com.example.leagueapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.leagueapp.R
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.ui.detailScreen.DetailScreen
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
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            BackgroundImage(modifier = Modifier.fillMaxSize())
            Navigation(navController = navController, innerPadding = innerPadding)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(innerPadding = innerPadding, navController = navController)
        }
        composable(
            route="champion/{championid}",
            arguments = listOf(navArgument("championid") {type = NavType.StringType})
        ) { backStackEntry ->
            val championId = backStackEntry.arguments?.getString("championid")
            championId?.let {championId ->
                DetailScreen(championId = championId)
            }
        }
    }
}

@Composable
fun BackgroundImage(modifier: Modifier = Modifier) {
    val backgroundImage = R.drawable.background
    Image(
        painter = painterResource(id = backgroundImage),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    )
}

