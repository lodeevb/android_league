package com.example.leagueapp.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.leagueapp.ui.components.Grid

@Composable
fun FavoriteScreen(innerPadding: PaddingValues, navController: NavHostController, viewModel : FavoriteScreenViewModel = viewModel(factory = FavoriteScreenViewModel.Factory)) {

    val championState by viewModel.championListState.collectAsState()

    if (championState.championList.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Grid(championList = championState.championList, navController =  navController)
        }
    }
    else {
        Text("Je hebt nog geen favoriete champions")
    }

}