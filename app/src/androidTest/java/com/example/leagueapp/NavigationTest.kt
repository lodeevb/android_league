package com.example.leagueapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.leagueapp.ui.LeagueApp
import com.example.leagueapp.ui.navigation.Destinations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    private val champId: String = "Aatrox"

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
        composeTestRule.runOnUiThread {
            navController.navigate(Destinations.All.name)
        }
        composeTestRule
            .onNodeWithText("all")
            .assertIsDisplayed()
    }
}