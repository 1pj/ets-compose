package com.ets.androiddev.presentation.home

import com.ets.androiddev.domain.datatypes.Async
import com.ets.androiddev.domain.datatypes.Uninitialized
import com.ets.androiddev.domain.entities.Place
import com.ets.androiddev.presentation.home.data.WeatherData

data class HomeViewState(
    val currentPlace: Place = Place.Tokyo,
    val weatherData: Async<WeatherData> = Uninitialized,
)
