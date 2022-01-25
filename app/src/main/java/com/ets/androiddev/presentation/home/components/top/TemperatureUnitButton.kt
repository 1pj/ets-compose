
package com.ets.androiddev.presentation.home.components.top

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ets.androiddev.R
import com.ets.androiddev.domain.entities.TemperatureUnit

@Composable
internal fun TemperatureUnitButton(
    current: TemperatureUnit,
    onChanged: (TemperatureUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = LocalContentColor.current.copy(alpha = 0.5f),
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                onClickLabel = stringResource(current.toClickLabel()),
                role = Role.Button
            ) {
                onChanged(
                    when (current) {
                        TemperatureUnit.Celsius -> TemperatureUnit.Fahrenheit
                        TemperatureUnit.Fahrenheit -> TemperatureUnit.Celsius
                    }
                )
            }
    ) {
        Text(
            text = if (current == TemperatureUnit.Celsius) {
                stringResource(R.string.common_fahrenheit_sign)
            } else {
                stringResource(R.string.common_celsius_sign)
            },
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clearAndSetSemantics {}
                .padding(
                    horizontal = dimensionResource(R.dimen.spacing_s),
                    vertical = dimensionResource(R.dimen.spacing_xs)
                )
        )
    }
}

private fun TemperatureUnit.toClickLabel() = when (this) {
    TemperatureUnit.Celsius -> R.string.temperature_unit_button_label_to_fahrenheit
    TemperatureUnit.Fahrenheit -> R.string.temperature_unit_button_label_to_celsius
}
