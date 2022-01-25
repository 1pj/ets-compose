package com.ets.androiddev.domain.entities

import java.time.ZonedDateTime

data class WeatherSnapshot(
    val dateTime: ZonedDateTime,
    val condition: Condition,
    val temperature: Temperature,
    val precipitation: Float,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Int,
)
