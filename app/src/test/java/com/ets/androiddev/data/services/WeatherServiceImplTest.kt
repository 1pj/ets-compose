package com.ets.androiddev.data.services

import com.ets.androiddev.di.AppModule
import com.ets.androiddev.domain.entities.Place
import io.kotest.matchers.floats.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.Test

class WeatherServiceImplTest {
    private val weatherService = AppModule.provideWeatherService()

    @Test
    fun `Service returns sensible values`() {
        with(weatherService.getCurrent(Place.NewYork)) {
            humidity shouldBeGreaterThan 0
            pressure shouldBeGreaterThan 0
            precipitation shouldBeGreaterThanOrEqual 0f
            windSpeed shouldBeGreaterThanOrEqual 0
            temperature.asCelsius() should { it > -80 && it < 80 }
        }
    }

    @Test
    fun `Week forecast returns proper number of days`() {
        val items = weatherService.getWeekForecast(Place.NewYork)
        items.size shouldBe 7
    }
}
