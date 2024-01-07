package com.example.leagueapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leagueapp.model.ChampionMin

/**
 * Composable function representing an item within the champion grid.
 *
 * @param champion The [champion][ChampionMin] to display in the grid item.
 * @param onItemClick Callback invoked when the grid item is clicked.
 */
@Composable
fun GridItem(champion: ChampionMin, onItemClick: () -> Unit){
    val context = LocalContext.current
    val resourceId = context.resources.getIdentifier(champion.id.lowercase(),"drawable",context.packageName)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onItemClick.invoke() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = champion.id,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxWidth()
            )
            Text(
                text = champion.name,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}