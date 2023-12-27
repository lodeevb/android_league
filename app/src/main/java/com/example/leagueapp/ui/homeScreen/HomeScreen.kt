package com.example.leagueapp.ui.homeScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leagueapp.model.ChampionMin
import com.example.leagueapp.ui.components.GridItem

@Composable
fun HomeScreen(champions: List<ChampionMin>, innerPadding: PaddingValues) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(innerPadding),
    ){
        items(champions) { champion ->
            GridItem(item = champion)
        }
    }
}