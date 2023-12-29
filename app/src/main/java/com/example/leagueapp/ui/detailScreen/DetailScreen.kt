package com.example.leagueapp.ui.detailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.leagueapp.ui.theme.Beaufort

@Composable
fun DetailScreen(viewModel : DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory),  championId: String) {
    LaunchedEffect(Unit) {
        viewModel.fetchChampionDetails(championId)
    }
    val detailChampionState by viewModel.detailChampionState.collectAsState()
    val championDetail = detailChampionState.championDetail
    BackgroundImage(modifier = Modifier.fillMaxSize(), championId)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
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
            Column(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = championDetail.lore,
                    textAlign = TextAlign.Justify,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun BackgroundImage(modifier: Modifier = Modifier, championId: String) {
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