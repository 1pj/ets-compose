
package com.ets.androiddev.presentation.home.data

import com.ets.androiddev.domain.entities.WeatherSnapshot

data class WeatherData(
    val current: WeatherSnapshot,
    val week: List<WeatherSnapshot>
)
