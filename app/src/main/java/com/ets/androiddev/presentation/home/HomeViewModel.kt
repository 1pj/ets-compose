package com.ets.androiddev.presentation.home

import com.ets.androiddev.core.mvi.MviComposeViewModel
import com.ets.androiddev.domain.services.WeatherService
import com.ets.androiddev.presentation.home.data.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherService: WeatherService
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
