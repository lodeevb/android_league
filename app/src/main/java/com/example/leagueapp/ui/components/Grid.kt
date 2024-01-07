package com.example.leagueapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.leagueapp.model.ChampionMin

/**
 * Composable function representing a grid of champions.
 *
 * @param championList The list of [champions][ChampionMin] to display in the grid.
 * @param navController [NavHostController] used for navigation.
 */
@Composable
fun Grid(championList: List<ChampionMin>, navController: NavHostController) {

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = if (isLandscape) 40.dp else 0.dp, end = if (isLandscape) 40.dp else 0.dp),
        contentAlignment = Alignment.Center,
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(if (isLandscape) 5 else 3),
            modifier = Modifier.padding(5.dp),
            content = {
                items(championList) { champion ->
                    GridItem(champion = champion) {
                        navController.navigate("champion/${champion.id}?isFavorite=${champion.isFavorite}")
                    }
                }
            }
        )
    }

}