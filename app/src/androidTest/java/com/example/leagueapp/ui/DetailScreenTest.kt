package com.example.leagueapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun init(){
        composeTestRule.setContent {
            LeagueApp()
        }
        composeTestRule.onNodeWithContentDescription("Aatrox").performClick()
    }

    @Test
    fun detailScreen_ShowNavigationIcons() {
        composeTestRule.onNodeWithContentDescription("All").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Favorites").assertIsDisplayed()
    }

    @Test
    fun detailScreen_ShowSpellInfo() {
        composeTestRule.onNodeWithContentDescription("AatroxQ")
            .performClick()

        composeTestRule.onNodeWithText("THE DARKIN BLADE").assertIsDisplayed()
    }
}