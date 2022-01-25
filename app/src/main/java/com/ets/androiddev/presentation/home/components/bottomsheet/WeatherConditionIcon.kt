
package com.ets.androiddev.presentation.home.components.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ets.androiddev.core.components.Cloud
import com.ets.androiddev.core.components.Sun
import com.ets.androiddev.core.theme.AppTheme
import com.ets.androiddev.domain.entities.Condition
import com.ets.androiddev.presentation.home.extensions.displayText

@Composable
internal fun WeatherConditionIcon(
    condition: Condition,
    modifier: Modifier = Modifier,
    contentDescription: String? = condition.displayText(),
) {
    val icon = when (condition) {
        Condition.Clear -> AppTheme.Icons.Sun
        Condition.Sunny -> AppTheme.Icons.Sun
        Condition.Fog -> AppTheme.Icons.Cloud
    }

    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
    )
}
