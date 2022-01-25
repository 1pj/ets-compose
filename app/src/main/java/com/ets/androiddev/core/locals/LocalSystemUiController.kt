package com.ets.androiddev.core.locals

import androidx.compose.runtime.compositionLocalOf

val LocalSystemUiController = compositionLocalOf<SystemUiController> {
    error("No SystemUiController provided.")
}
