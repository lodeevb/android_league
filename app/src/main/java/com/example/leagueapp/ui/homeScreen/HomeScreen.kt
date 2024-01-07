package com.example.leagueapp.ui.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.leagueapp.ui.components.Grid


/**
 * Composable function representing the HomeScreen UI.
 *
 * @param innerPadding Padding values for the inner content.
 * @param viewModel [HomeScreenViewModel] to manage data for the HomeScreen.
 * @param navController [NavHostController] used for navigation.
 */
@Composable
fun HomeScreen(innerPadding: PaddingValues, viewModel : HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
               navController: NavHostController
) {
    val championState by viewModel.championListState.collectAsState()

    if (championState.championList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
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
                        text = "You need to be connected to the internet to load the champions for the first time.",
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }

    else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Grid(championList = championState.championList, navController =  navController)
        }
    }
}