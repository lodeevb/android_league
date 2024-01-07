package com.example.leagueapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * Composable function representing the top app bar which is hidden.
 *
 * @param modifier Modifier to be applied to the [TopBar].
 * @param title Optional title resource ID for the app bar.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int?,
) {
    title?.let {
        androidx.compose.material3.TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = stringResource(it),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            },
        )
    }
}