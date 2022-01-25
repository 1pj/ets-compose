
package com.ets.androiddev.presentation.home.extensions

import java.time.ZonedDateTime

internal fun ZonedDateTime.isNightTime() = hour < 4 || hour > 20
