package com.ets.androiddev.presentation.home

import com.ets.androiddev.core.mvi.MviComposeViewModel
import com.ets.androiddev.di.AppModule
import com.ets.androiddev.domain.services.WeatherService
import com.ets.androiddev.presentation.home.data.WeatherData

// TODO: Provide proper DI using dagger/hilt
class HomeViewModel(
    private val weatherService: WeatherService = AppModule.provideWeatherService()
) : MviComposeViewModel<HomeViewState, HomeCommand>(HomeViewState()) {

    override fun onCommand(command: HomeCommand) {
        when (command) {
            is HomeCommand.LoadData -> {
                executeCatching({
                    WeatherData(
                        current = weatherService.getCurrent(command.place),
                        week = weatherService.getWeekForecast(command.place)
                    )
                }) {
                    copy(currentPlace = command.place, weatherData = it)
                }
            }
        }
    }
}
