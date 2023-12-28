package com.example.leagueapp.ui.detailScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(viewModel : DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory),  championid: String) {
    val currentViewModel by remember { mutableStateOf(viewModel) }
    LaunchedEffect(Unit) {
        currentViewModel.getChampDetails(championid)
    }
}