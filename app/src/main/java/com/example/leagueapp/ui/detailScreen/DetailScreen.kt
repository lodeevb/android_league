package com.example.leagueapp.ui.detailScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leagueapp.ui.components.Spells
import com.example.leagueapp.ui.theme.Beaufort

@Composable
fun DetailScreen(viewModel : DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory),  championId: String) {
    LaunchedEffect(Unit) {
        viewModel.fetchChampionDetails(championId)
    }
    val detailChampionState by viewModel.detailChampionState.collectAsState()
    val championDetail = detailChampionState.champion.championDetail
    val spells = detailChampionState.champion.spells

    BackgroundImage(championId)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.Top
        ){
            Text(
                text = championDetail.title.uppercase(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = Beaufort,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = championDetail.name.uppercase(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = Beaufort,
                fontSize = 62.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            ScrollableLore(championDetail.lore)
            Spacer(modifier = Modifier.height(50.dp))
            Spells(spells)
        }
    }
}

@Composable
fun ScrollableLore(lore: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color(0xFF3F454D)),
                shape = MaterialTheme.shapes.medium
            )
            .background(Color.Black.copy(alpha = 0.75f), shape = MaterialTheme.shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = lore,
                textAlign = TextAlign.Justify,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
    }
}

@Composable
fun BackgroundImage(championId: String) {
    val context = LocalContext.current
    var name = championId.lowercase() + "full"
    val resourceId = context.resources.getIdentifier(name,"drawable",context.packageName)
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        alignment = Alignment.TopCenter,
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    )
}