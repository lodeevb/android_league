package com.example.leagueapp.ui.detailScreen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.leagueapp.ui.components.Spells
import com.example.leagueapp.ui.theme.Beaufort

/**
 * Composable function representing the detail screen for a specific champion.
 *
 * @param championId The ID of the champion to display details for.
 * @param isFavorite A flag indicating if the champion is marked as favorite.
 * @param viewModel [DetailScreenViewModel] to manage data for the DetailScreen.
 * @param navController [NavHostController] used for navigation.
 */
@Composable
fun DetailScreen(viewModel : DetailScreenViewModel = viewModel(factory = DetailScreenViewModel.Factory),  championId: String, isFavorite: Boolean,
                 navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(Unit) {
        viewModel.fetchChampionDetails(championId)
    }

    val detailScreenState = viewModel.detailScreenState

    if (detailScreenState is DetailScreenState.Error) {
        NoInternet(navController)
    }
    else {
        val detailChampionState by viewModel.detailChampionState.collectAsState()
        val championDetail = detailChampionState.champion.championDetail
        val spells = detailChampionState.champion.spells

        var favoriteBool by remember { mutableStateOf(isFavorite) }
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            BackgroundImage(championId)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    verticalArrangement = Arrangement.Top
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Text(
                            text = championDetail.title.uppercase(),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = Beaufort,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = championDetail.name.uppercase(),
                        textAlign = TextAlign.Center,
                        maxLines = 5,
                        color = Color.White,
                        fontFamily = Beaufort,
                        fontSize = 62.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ScrollableLore(championDetail.lore, isLandscape)
                    Spacer(modifier = Modifier.height(50.dp))
                    Spells(spells, isLandscape)
                    Button(
                        onClick = {
                            viewModel.updateFavorite(championId)
                            favoriteBool = !favoriteBool
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 25.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black.copy(alpha = 0.75f),
                        ),
                        border = BorderStroke(1.dp, Color.White),
                    ) {
                        Text(
                            text = if (favoriteBool) {
                                "Remove from favorites."
                            } else {
                                "Add to favorites."
                            }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Composable function for displaying scrollable lore text.
 *
 * @param lore The lore text to display.
 * @param isLandscape Boolean indicating landscape mode.
 */
@Composable
fun ScrollableLore(lore: String, isLandscape: Boolean){
    Box(
        modifier = Modifier
            .padding(
                start = if (isLandscape) 60.dp else 0.dp,
                end = if (isLandscape) 60.dp else 0.dp
            )
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color.White),
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

/**
 * Composable function for displaying the background image for a champion.
 *
 * @param championId The ID of the champion to display the background image for.
 */
@Composable
fun BackgroundImage(championId: String) {
    val context = LocalContext.current
    val name = championId.lowercase() + "full"
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

/**
 * Composable function displaying a no-internet message.
 *
 * @param navController [NavHostController] used for navigation.
 */
@Composable
fun NoInternet(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 25.dp, bottom = 0.dp, end = 25.dp)
                    .border(
                        BorderStroke(1.dp, Color(0xFF3F454D)),
                        shape = MaterialTheme.shapes.medium
                    )
                    .background(
                        Color.Black.copy(alpha = 0.75f),
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Oops! Seems like the champion won't show himself for the first time without being connected to the internet.",
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

        }
    }
}