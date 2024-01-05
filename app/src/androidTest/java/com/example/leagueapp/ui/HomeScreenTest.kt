package com.example.leagueapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init(){
        composeTestRule.setContent {
            LeagueApp()
        }
    }

    @Test
    fun homeScreen_IconsDisplayed() {
        composeTestRule.onNodeWithContentDescription("All").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Favorites").assertIsDisplayed()
    }

    @Test
    fun homeScreen_ChampionsDisplayed() {
        composeTestRule.onNodeWithContentDescription("Aatrox").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Bard").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Blitzcrank").assertIsDisplayed()
    }
}