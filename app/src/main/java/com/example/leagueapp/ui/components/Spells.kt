package com.example.leagueapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leagueapp.data.database.spell.dbSpell

@Composable
fun Spells(spells: List<dbSpell>) {
    var selectedSpell by remember { mutableStateOf<dbSpell?>(null) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(spells) { spell ->
                    val context = LocalContext.current
                    val resourceId = context.resources.getIdentifier(spell.id.lowercase(),"drawable",context.packageName)
                    Box(
                        modifier = Modifier
                            .size(if (selectedSpell == spell) 85.dp else 75.dp)
                            .clickable {
                                selectedSpell = if (selectedSpell == spell) null else spell
                            }
                            .background(
                                color = Color.Black.copy(alpha = if (selectedSpell == spell) 1f else 0.5f)
                            )
                    ) {
                        Image(
                            painter = painterResource(id = resourceId),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            if (selectedSpell != null) {
                Box {
                    Column {
                        Text(
                            text = selectedSpell!!.name,
                            textAlign = TextAlign.Justify,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        )
                    }
                }
            }
        }
    }
}