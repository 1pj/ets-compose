package com.ets.androiddev.presentation.home.components.top

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.ets.androiddev.core.theme.AppTheme
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.presentation.launcher.LauncherActivity
import io.kotest.matchers.shouldBe
import org.junit.Rule
import org.junit.Test

private const val ButtonTag = "TemperatureUnitButton"

class TemperatureUnitButtonTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LauncherActivity>()

    @Test
    fun clickingChangesTemperatureUnit() {
        var temperatureUnit = TemperatureUnit.Celsius

        composeTestRule.setContent {
            AppTheme {
                TemperatureUnitButton(
                    current = temperatureUnit,
                    onChanged = { temperatureUnit = it },
                    modifier = Modifier.testTag(ButtonTag)
                )
            }
        }
        composeTestRule.onNodeWithTag(ButtonTag).performClick()

        temperatureUnit shouldBe TemperatureUnit.Fahrenheit
    }
}
