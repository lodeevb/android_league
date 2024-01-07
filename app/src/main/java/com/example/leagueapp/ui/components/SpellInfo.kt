package com.example.leagueapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leagueapp.data.database.spell.dbSpell
import com.example.leagueapp.ui.theme.Beaufort

/**
 * Composable function displaying a box containing spell information.
 *
 * @param spell The [spell][dbSpell] details to display.
 */
@Composable
fun SpellInfo(spell: dbSpell) {
    Box (
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.White),
                shape = MaterialTheme.shapes.medium
            )
            .background(Color.Black.copy(alpha = 0.75f), shape = MaterialTheme.shapes.medium)
    ){
        Column {
            Text(
                text = spell.name.uppercase(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = Beaufort,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = spell.description,
                textAlign = TextAlign.Justify,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}