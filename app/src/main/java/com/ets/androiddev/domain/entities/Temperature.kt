package com.ets.androiddev.domain.entities

sealed class Temperature {

    class Fahrenheit(val value: Float) : Temperature()

    class Celsius(val value: Float) : Temperature()

    fun asCelsius(): Float = when (this) {
        is Celsius -> value
        is Fahrenheit -> value.toCelsius()
    }

    fun asFahrenheit(): Float = when (this) {
        is Celsius -> value.toFahrenheit()
        is Fahrenheit -> value
    }

    fun getValue(unit: TemperatureUnit) = when (unit) {
        TemperatureUnit.Celsius -> asCelsius()
        TemperatureUnit.Fahrenheit -> asFahrenheit()
    }

    private fun Float.toCelsius() = (this - 32) / 1.8f

    private fun Float.toFahrenheit() = this * 1.8f + 32
}
