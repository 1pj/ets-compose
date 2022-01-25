package com.ets.androiddev.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import com.ets.androiddev.core.components.OptionDialog
import com.ets.androiddev.core.components.OptionDialogItem
import com.ets.androiddev.core.theme.AppTheme
import com.ets.androiddev.core.theme.Colors
import com.ets.androiddev.domain.datatypes.Loading
import com.ets.androiddev.domain.datatypes.Success
import com.ets.androiddev.domain.entities.Place
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.presentation.home.components.bottomsheet.ForecastBlock
import com.ets.androiddev.presentation.home.components.top.WeatherBlock
import com.ets.androiddev.presentation.home.extensions.displayText
import com.ets.androiddev.presentation.home.extensions.isNightTime

private val SheetPeekHeight = 200.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    state: HomeViewState,
    commands: (HomeCommand) -> Unit
) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    var showPlaceSelection by rememberSaveable { mutableStateOf(false) }
    var temperatureUnit by rememberSaveable { mutableStateOf(TemperatureUnit.Celsius) }

    Box(Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetState,
            sheetContent = {
                state.weatherData()?.let {
                    ForecastBlock(
                        weather = it,
                        temperatureUnit = temperatureUnit
                    )
                }
            },
            sheetPeekHeight = with(LocalDensity.current) { SheetPeekHeight.toDp() },
        ) {
            when (state.weatherData) {
                is Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is Success -> {
                    val weather = state.weatherData.unwrap().current

                    CompositionLocalProvider(
                        LocalContentColor provides if (weather.dateTime.isNightTime()) {
                            Colors.Alabaster
                        } else {
                            Colors.Mirage
                        }
                    ) {
                        WeatherBlock(
                            place = state.currentPlace,
                            weather = weather,
                            temperatureUnit = temperatureUnit,
                            onChangePlace = { showPlaceSelection = true },
                            onChangeTemperatureUnit = { temperatureUnit = it }
                        )
                    }
                }
                else -> Unit
            }
        }

        if (showPlaceSelection) {
            AppTheme.OptionDialog(
                items = Place.values().map { place ->
                    OptionDialogItem(
                        title = place.displayText(),
                        action = {
                            showPlaceSelection = false
                        }
                    )
                },
                onDismissRequest = {
                    showPlaceSelection = false
                }
            )
        }
    }

    LaunchedEffect(state.currentPlace) {
        commands(HomeCommand.LoadData(state.currentPlace))
    }
}
