
package com.ets.androiddev.presentation.home.components.bottomsheet

import androidx.compose.runtime.Composable
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.domain.entities.WeatherSnapshot

@Composable
internal fun WeatherDetailsBlock(
    title: String,
    weather: WeatherSnapshot,
    temperatureUnit: TemperatureUnit,
    isHeader: Boolean = false,
    isExpanded: Boolean = false
) {
    // TODO: Implement expandable (with animation) block which has title (h6),
    //  weather condition icon, and a set of properties according to design
}
