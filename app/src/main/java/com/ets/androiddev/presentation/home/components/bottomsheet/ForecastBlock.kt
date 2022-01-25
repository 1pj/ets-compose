
package com.ets.androiddev.presentation.home.components.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import com.ets.androiddev.R
import com.ets.androiddev.core.components.Spacers
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.presentation.home.data.WeatherData
import com.ets.androiddev.presentation.home.extensions.getName
import com.google.accompanist.insets.navigationBarsPadding

@Composable
internal fun ForecastBlock(
    weather: WeatherData,
    temperatureUnit: TemperatureUnit
) {
    require(weather.week.size == 7) { "Weekly forecast is expected." }

    val blockLabel = stringResource(R.string.bottom_sheet_forecast_label)

    Column(
        modifier = Modifier
            .semantics { paneTitle = blockLabel }
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(vertical = dimensionResource(R.dimen.spacing_m))
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHandle(
            Modifier.align(Alignment.CenterHorizontally)
        )
        Spacers.S()

        WeatherDetailsBlock(
            title = stringResource(R.string.common_weekdays_today),
            weather = weather.current,
            temperatureUnit = temperatureUnit,
            isHeader = true,
            isExpanded = true
        )

        weather.week.drop(1).forEachIndexed { i, item ->
            if (i > 0) Divider()
            Spacers.Xs()

            WeatherDetailsBlock(
                title = item.dateTime.dayOfWeek.getName(),
                weather = item,
                temperatureUnit = temperatureUnit
            )
            Spacers.Xs()
        }
    }
}
