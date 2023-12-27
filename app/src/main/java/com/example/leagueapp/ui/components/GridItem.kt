package com.example.leagueapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leagueapp.model.ChampionMin

@Composable
fun GridItem(item: ChampionMin){
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
/*        Image(
            painter = painterResource(id = null),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .fillMaxWidth()
        )*/
        Text(
            text = item.name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}