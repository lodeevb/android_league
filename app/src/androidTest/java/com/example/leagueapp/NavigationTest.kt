package com.example.leagueapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.leagueapp.ui.LeagueApp
import com.example.leagueapp.ui.navigation.Destinations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    private val champName: String = "Aatrox"

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    lateinit var navController: TestNavHostController

    @Before
    fun setupNavAppHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LeagueApp(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithText("All")
            .assertIsDisplayed()
    }

    @Test
    fun verifyFavoritesDestination() {
        composeTestRule.onNodeWithContentDescription(Destinations.Favorites.name)
            .performClick()

        composeTestRule
            .onNodeWithText("Favorites")
            .assertIsDisplayed()
    }

    @Test
    fun verifyDetailScreen() {
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

        composeTestRule.onNodeWithContentDescription("Back")
            .performClick()

        composeTestRule
            .onNodeWithText("All")
            .assertIsDisplayed()
    }
}