package com.ets.androiddev.presentation.home.components.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ets.androiddev.R
import com.ets.androiddev.core.components.Spacers
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.domain.entities.WeatherSnapshot
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun WeatherDetailsBlock(
    title: String,
    weather: WeatherSnapshot,
    temperatureUnit: TemperatureUnit,
    isHeader: Boolean = false,
    isExpanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(
                vertical = dimensionResource(R.dimen.spacing_xs),
                horizontal = dimensionResource(R.dimen.spacing_m)
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = stringResource(
                    R.string.common_temperature,
                    weather.temperature.getValue(temperatureUnit).roundToInt()
                ),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }

        AnimatedVisibility(visible = isExpanded or isHeader) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = dimensionResource(R.dimen.spacing_s))
                ) {
                    WeatherConditionIcon(condition = weather.condition)
                    Spacers.Xs()
                    Text(text = weather.condition.name)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.spacing_s))
                ) {
                    Column {
                        Text(
                            text = stringResource(
                                R.string.weather_properties_humidity,
                                weather.humidity
                            ),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(text = stringResource(R.string.weather_properties_humidity_description))
                    }
                    Column {
                        Text(
                            text = stringResource(
                                R.string.weather_properties_precipitation, weather.precipitation
                            ),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(text = stringResource(R.string.weather_properties_precipitation_description))
                    }
                    Column {
                        Text(
                            text = stringResource(
                                R.string.weather_properties_pressure, weather.pressure
                            ),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(text = stringResource(R.string.weather_properties_pressure_description))
                    }
                    Column {
                        Text(
                            text = stringResource(
                                R.string.weather_properties_wind, weather.windSpeed
                            ),
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(text = stringResource(R.string.weather_properties_wind_description))
                    }
                }
            }
        }
    }
}
