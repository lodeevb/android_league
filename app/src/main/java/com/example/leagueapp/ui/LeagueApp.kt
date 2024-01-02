package com.example.leagueapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.leagueapp.R
import com.example.leagueapp.ui.detailScreen.DetailScreen
import com.example.leagueapp.ui.favorites.FavoriteScreen
import com.example.leagueapp.ui.homeScreen.HomeScreen
import com.example.leagueapp.ui.navigation.BottomBar
import com.example.leagueapp.ui.navigation.Destinations

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LeagueApp(navController: NavHostController = rememberNavController()) {

    var selectedItem by remember { mutableStateOf(0) }
    var items = listOf("All", "Favorites")
    val icons = listOf(Icons.Outlined.Home, Icons.Outlined.FavoriteBorder)

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val startPadding = if (isLandscape) 75.dp else 0.dp
    val bottomPadding = if (isLandscape) 0.dp else 75.dp

    Scaffold (
        bottomBar = {
            BottomBar(
                items = items,
                icons = icons,
                selectedItem = selectedItem,
                onItemSelected = { index ->
                    selectedItem = index
                    navController.navigate(Destinations.values()[index].name)
                },
            )
        },
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = startPadding, bottom = bottomPadding)
        ) {
            BackgroundImage(modifier = Modifier.fillMaxSize())
            Navigation(navController = navController, innerPadding = innerPadding)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "all") {
        composable(route = "all") {
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
        composable(route = "favorites") {
            FavoriteScreen(innerPadding = innerPadding, navController = navController)
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

