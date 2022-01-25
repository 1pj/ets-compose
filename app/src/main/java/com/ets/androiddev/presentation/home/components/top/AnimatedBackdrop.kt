
package com.ets.androiddev.presentation.home.components.top

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ets.androiddev.R
import com.ets.androiddev.domain.entities.Condition
import com.ets.androiddev.domain.entities.WeatherSnapshot
import com.ets.androiddev.presentation.home.extensions.isNightTime

private const val AnimationDuration = 3000

@Composable
internal fun AnimatedBackdrop(weather: WeatherSnapshot) {
    val (base, light) = getBackdropImages(weather)
    var lightAlpha by remember { mutableStateOf(0f) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(base),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(light),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            alpha = lightAlpha,
            modifier = Modifier.fillMaxSize()
        )
    }

    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(AnimationDuration),
                repeatMode = RepeatMode.Reverse
            )
        ) { value, _ ->
            lightAlpha = value
        }
    }
}

@Composable
private fun getBackdropImages(weather: WeatherSnapshot) = when (weather.condition) {
    Condition.Clear -> {
        if (weather.dateTime.isNightTime()) {
            R.drawable.night_base to R.drawable.night_volume_light
        } else {
            R.drawable.day_base to R.drawable.day_volume_light
        }
    }
    Condition.Sunny -> {
        if (weather.dateTime.isNightTime()) {
            R.drawable.night_base to R.drawable.night_volume_light
        } else {
            R.drawable.day_base to R.drawable.day_volume_light
        }
    }
    Condition.Fog -> {
        if (weather.dateTime.isNightTime()) {
            R.drawable.night_fog_base to R.drawable.night_fog_volume_light
        } else {
            R.drawable.day_fog_base to R.drawable.day_fog_base_volume_light
        }
    }
}
