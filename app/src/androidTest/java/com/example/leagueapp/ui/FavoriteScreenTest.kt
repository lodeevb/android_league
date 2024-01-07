package com.example.leagueapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavoriteScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()



    @Before
    fun init(){
        composeTestRule.setContent {
            LeagueApp()
        }
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
    }

    @Test
    fun favoriteScreen_IconsDisplayed() {
        composeTestRule.onNodeWithContentDescription("All").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Favorites").assertIsDisplayed()
    }

}