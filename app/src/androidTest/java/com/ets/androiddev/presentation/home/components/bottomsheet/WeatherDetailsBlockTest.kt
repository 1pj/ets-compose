package com.ets.androiddev.presentation.home.components.bottomsheet

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import com.ets.androiddev.R
import com.ets.androiddev.core.theme.AppTheme
import com.ets.androiddev.domain.entities.Condition
import com.ets.androiddev.domain.entities.Temperature
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.domain.entities.WeatherSnapshot
import com.ets.androiddev.presentation.launcher.LauncherActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime

private const val TestTitle = "TestTitle"
private val TestSnapshot = WeatherSnapshot(
    dateTime = ZonedDateTime.now(),
    condition = Condition.Clear,
    temperature = Temperature.Celsius(42f),
    precipitation = 0f,
    humidity = 50,
    pressure = 1000,
    windSpeed = 0,
)

class WeatherDetailsBlockTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LauncherActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            AppTheme {
                WeatherDetailsBlock(
                    title = TestTitle,
                    weather = TestSnapshot,
                    temperatureUnit = TemperatureUnit.Celsius,
                    isHeader = false,
                    isExpanded = false
                )
            }
        }
    }

    @Test
    fun canBeExpanded(): Unit = with(composeTestRule.activity) {
        with(composeTestRule) {
            onNodeWithText(
                getString(R.string.weather_properties_humidity_description),
                useUnmergedTree = true
            ).assertDoesNotExist()
            onNodeWithText(TestTitle, useUnmergedTree = true).onParent()
                .performClick()
            onNodeWithText(
                text = getString(R.string.weather_properties_humidity_description),
                useUnmergedTree = true
            ).assertExists()
        }
    }
}
