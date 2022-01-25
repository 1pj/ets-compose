package com.ets.androiddev.core.theme

import androidx.compose.material.darkColors

internal val DarkColorPalette = with(Colors) {
    darkColors(
        primary = White,
        secondary = Orange,
        background = Mirage,
        surface = BigStone,
        onPrimary = Mirage,
        onSecondary = White,
        onBackground = Alabaster,
        onSurface = White
    )
}
