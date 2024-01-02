package com.example.leagueapp.ui.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    items: List<String>,
    icons: List<ImageVector>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        NavigationRail(
            containerColor = Color(0xFF3C3C3C),
            contentColor = Color.White,
            modifier = Modifier.fillMaxHeight()
                .width(75.dp),
        ) {
            Spacer(Modifier.weight(1f))
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                            tint = if (selectedItem == index) Color.Gray else Color.White
                        )
                    },
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        indicatorColor = Color.White,
                    ),
                )
            }
            Spacer(Modifier.weight(1f))
        }
    } else {
        NavigationBar(
            containerColor = Color(0xFF3C3C3C),
            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth()
                .height(75.dp),
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                            tint = if (selectedItem == index) Color.Gray else Color.White
                        )
                    },
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        indicatorColor = Color.White,
                    ),
                )
            }
        }
    }
}