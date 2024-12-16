package com.htsoft.weather.android.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class WeatherEmptyScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherEmptyScreen_displaysMessage() {
        val noCitySelectedText = "No city selected"
        val selectCityHintText = "Select a city to see the weather"

        composeTestRule.setContent {
            WeatherEmptyScreen(
                title = noCitySelectedText, subtitle = selectCityHintText
            )
        }

        composeTestRule.onNodeWithText(noCitySelectedText)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(selectCityHintText)
            .assertIsDisplayed()
    }
}
