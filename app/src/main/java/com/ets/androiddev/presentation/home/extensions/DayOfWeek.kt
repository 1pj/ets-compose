
package com.ets.androiddev.presentation.home.extensions

import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

internal fun DayOfWeek.getName() = getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())
