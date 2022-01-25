package com.ets.androiddev.domain.services

import com.ets.androiddev.domain.entities.Place
import com.ets.androiddev.domain.entities.WeatherSnapshot

interface WeatherService {
    fun getCurrent(place: Place): WeatherSnapshot

    fun getWeekForecast(place: Place): List<WeatherSnapshot>
}
