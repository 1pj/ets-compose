package com.ets.androiddev.presentation.home.components.top

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ets.androiddev.R
import com.ets.androiddev.core.locals.LocalSystemUiController
import com.ets.androiddev.core.locals.SystemUiController
import com.ets.androiddev.core.theme.AppTheme
import com.ets.androiddev.domain.entities.Condition
import com.ets.androiddev.domain.entities.Place
import com.ets.androiddev.domain.entities.Temperature
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.domain.entities.WeatherSnapshot
import com.ets.androiddev.presentation.launcher.LauncherActivity
import com.google.accompanist.insets.ProvideWindowInsets
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.ZonedDateTime
import kotlin.math.roundToInt

private const val TestTemperature = 42f
private val TestSnapshot = WeatherSnapshot(
    dateTime = ZonedDateTime.now(),
    condition = Condition.Clear,
    temperature = Temperature.Celsius(TestTemperature),
    precipitation = 0f,
    humidity = 50,
    pressure = 1000,
    windSpeed = 0,
)

class WeatherBlockTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LauncherActivity>()

    @Before
    fun setup() {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalSystemUiController provides remember {
                    SystemUiController(composeTestRule.activity.window)
                }
            ) {
                ProvideWindowInsets {
                    AppTheme {
                        WeatherBlock(
                            place = Place.Sydney,
                            weather = TestSnapshot,
                            temperatureUnit = TemperatureUnit.Celsius,
                            onChangePlace = {},
                            onChangeTemperatureUnit = {}
                        )
                    }
                }
            }
        }
    }

    @Test
    fun showsProperTemperature() {
        val temperatureText = composeTestRule.activity
            .getString(R.string.common_temperature, TestTemperature.roundToInt())
        composeTestRule.onNodeWithText(temperatureText).assertIsDisplayed()
    }

    @Test
    fun showsProperCity() {
        val city = composeTestRule.activity.getString(R.string.places_sydney)
        composeTestRule.onNodeWithText(city).assertIsDisplayed()
    }
}
