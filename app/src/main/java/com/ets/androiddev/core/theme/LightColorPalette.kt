package com.ets.androiddev.core.theme

import androidx.compose.material.lightColors

// TODO: Ensure that app automatically switches to LightColorPalette
//  following android settings
internal val LightColorPalette = with(Colors) {
    lightColors(
        primary = Orange,
        secondary = Orange,
        background = Alabaster,
        surface = White,
        onPrimary = White,
        onSecondary = White,
        onBackground = Mirage,
        onSurface = Mirage
    )
}
