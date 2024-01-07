package com.example.leagueapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
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

    @Test
    fun homeScreen_ClickOnChampionInfoDisplayed() = runTest {
        composeTestRule.onNodeWithContentDescription("Aatrox")
            .performClick()

        var isNodeDisplayed = false
        var attempt = 0
        val maxAttempts = 5
        val delayMillis = 500

        while (!isNodeDisplayed && attempt < maxAttempts) {
            try {
                composeTestRule.onNodeWithText("THE DARKIN BLADE").assertIsDisplayed()
                isNodeDisplayed = true
            } catch (e: Throwable) {
                Thread.sleep(delayMillis.toLong())
                attempt++
            }
        }
        assertTrue(isNodeDisplayed)
    }
}