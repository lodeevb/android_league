package com.example.leagueapp.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.leagueapp.R
import com.example.leagueapp.ui.components.Grid

@Composable
fun HomeScreen(innerPadding: PaddingValues, viewModel : HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
               navController: NavHostController
) {
    val championState by viewModel.championListState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Grid(championList = championState.championList, navController =  navController)
    }
}