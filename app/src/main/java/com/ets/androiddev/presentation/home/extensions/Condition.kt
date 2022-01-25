package com.ets.androiddev.presentation.home.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ets.androiddev.R
import com.ets.androiddev.domain.entities.Condition

@Composable
internal fun Condition.displayText(): String = when (this) {
    Condition.Clear -> stringResource(R.string.common_weather_condition_clear)
    Condition.Sunny -> stringResource(R.string.common_weather_condition_sunny)
    Condition.Fog -> stringResource(R.string.common_weather_condition_fog)
}
