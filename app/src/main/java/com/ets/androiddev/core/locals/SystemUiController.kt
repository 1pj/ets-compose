package com.ets.androiddev.core.locals

import android.view.View
import android.view.Window
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb

class SystemUiController(private val window: Window) {

    fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f
    ) {
        window.statusBarColor = color.toArgb()
        @Suppress("DEPRECATION")
        if (darkIcons) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility and
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }
}
