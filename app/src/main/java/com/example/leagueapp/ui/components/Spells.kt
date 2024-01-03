package com.example.leagueapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import com.example.leagueapp.data.database.spell.dbSpell

@Composable
fun Spells(spells: List<dbSpell>, isLandscape: Boolean) {
    var selectedSpell by remember { mutableStateOf<dbSpell?>(null) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .padding(start = if (isLandscape) 60.dp else 0.dp, end = if (isLandscape) 60.dp else 0.dp, top = 10.dp)
            .fillMaxSize()
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
                            .border(if (selectedSpell == spell) BorderStroke(3.dp, Color(0xFFC5A34F)) else BorderStroke(1.dp, Color(0xFFC5A34F)))
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
            Spacer(modifier = Modifier.height(20.dp))
            if (selectedSpell != null) {
                SpellInfo(selectedSpell!!)
            }
        }
    }
}