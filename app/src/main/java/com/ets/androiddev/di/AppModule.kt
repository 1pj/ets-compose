package com.ets.androiddev.di

import com.ets.androiddev.data.services.WeatherServiceImpl

// TODO: Provide proper DI using dagger/hilt
object AppModule {

    fun provideWeatherService() = WeatherServiceImpl()
}
