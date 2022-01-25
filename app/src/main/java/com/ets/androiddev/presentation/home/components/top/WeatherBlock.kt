
package com.ets.androiddev.presentation.home.components.top

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import com.ets.androiddev.R
import com.ets.androiddev.core.components.Spacers
import com.ets.androiddev.core.locals.LocalSystemUiController
import com.ets.androiddev.domain.entities.Place
import com.ets.androiddev.domain.entities.TemperatureUnit
import com.ets.androiddev.domain.entities.WeatherSnapshot
import com.ets.androiddev.presentation.home.extensions.displayText
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.roundToInt

@Composable
internal fun WeatherBlock(
    place: Place,
    weather: WeatherSnapshot,
    temperatureUnit: TemperatureUnit,
    onChangePlace: () -> Unit,
    onChangeTemperatureUnit: (TemperatureUnit) -> Unit
) {
    // TODO: Ensure that LocalSystemUiController is provided from outer composables
    val systemUiController = LocalSystemUiController.current
    val contentColor = LocalContentColor.current

    Box(Modifier.fillMaxSize()) {
        AnimatedBackdrop(weather)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(dimensionResource(R.dimen.spacing_l))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(
                        onClickLabel = stringResource(R.string.change_place_button_label),
                        role = Role.Button,
                        onClick = onChangePlace
                    )
                    .padding(dimensionResource(R.dimen.spacing_xs))
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationCity,
                    contentDescription = null,
                )
                Spacers.Xs()

                Text(
                    text = place.displayText(),
                    style = MaterialTheme.typography.h5,
                )
            }

            Text(
                text = stringResource(
                    R.string.common_temperature,
                    weather.temperature.getValue(temperatureUnit).roundToInt()
                ),
                style = MaterialTheme.typography.h2.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }

        TemperatureUnitButton(
            current = temperatureUnit,
            onChanged = onChangeTemperatureUnit,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.spacing_xs))
                .padding(dimensionResource(R.dimen.spacing_l))
                .statusBarsPadding()
                .align(Alignment.TopEnd)
        )
    }

    LaunchedEffect(contentColor) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = contentColor.luminance() < 0.5f
        )
    }
}
