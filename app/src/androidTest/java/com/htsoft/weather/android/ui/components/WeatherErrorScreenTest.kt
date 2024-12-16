package com.htsoft.weather.android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class WeatherErrorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorScreen_displaysIconAndMessage() {
        val errorMessage = "Something went wrong"
        val iconDescription = "Error icon"

        composeTestRule.setContent {
            WeatherErrorScreen(
                icon = Icons.Filled.Error,
                errorMessage = errorMessage,
                contentDescription = iconDescription
            )
        }

        composeTestRule.onNodeWithText(errorMessage)
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(iconDescription)
            .assertExists()
            .assertIsDisplayed()
    }
}
