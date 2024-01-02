package com.example.leagueapp.ui.navigation

import androidx.annotation.StringRes
import com.example.leagueapp.R

enum class Destinations (@StringRes val title: Int) {
    All(title = R.string.all),
    Favorites(title = R.string.favorites),
}