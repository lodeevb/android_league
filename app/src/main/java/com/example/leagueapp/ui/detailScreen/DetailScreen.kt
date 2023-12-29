package com.example.leagueapp.ui.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leagueapp.ui.theme.Beaufort

@Composable
fun DetailScreen(viewModel : DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory),  championId: String) {
    LaunchedEffect(Unit) {
        viewModel.fetchChampionDetails(championId)
    }
    val detailChampionState by viewModel.detailChampionState.collectAsState()
    val championDetail = detailChampionState.championDetail

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ){
            Text(
                text = championDetail.title.uppercase(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = Beaufort,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = championDetail.name.uppercase(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = Beaufort,
                fontSize = 32.sp,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}