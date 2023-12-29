package com.example.leagueapp.ui.detailScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailScreen(viewModel : DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory),  championid: String) {
    viewModel.fetchChampionDetails(championid)
    val championState by viewModel.detailChampionState.collectAsState()
    Text(
        text = championState.championDetail.lore,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
    )
}