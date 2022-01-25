package com.ets.androiddev.data.services

import com.ets.androiddev.domain.entities.Condition
import com.ets.androiddev.domain.entities.Place
import com.ets.androiddev.domain.entities.Temperature
import com.ets.androiddev.domain.entities.WeatherSnapshot
import com.ets.androiddev.domain.services.WeatherService
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.random.Random

class WeatherServiceImpl : WeatherService {
    private val rnd = Random(System.currentTimeMillis())
    private val data = Place.values().associate { place ->
        place to (0..6).map { i ->
            createRandomSnapshot(
                dateTime = place.getDateTime().plusDays(i.toLong()),
                condition = if (i == 0) {
                    place.getCondition()
                } else {
                    createRandomCondition()
                }
            )
        }
    }

    override fun getCurrent(place: Place): WeatherSnapshot {
        return data[place]!!.first()
    }

    override fun getWeekForecast(place: Place): List<WeatherSnapshot> {
        return data[place]!!
    }

    private fun createRandomSnapshot(
        dateTime: ZonedDateTime,
        condition: Condition = createRandomCondition()
    ) = WeatherSnapshot(
        dateTime = dateTime,
        condition = condition,
        temperature = Temperature.Celsius(rnd.nextInt(-5, 23).toFloat()),
        precipitation = if (condition != Condition.Clear) rnd.nextFloat() * 4 else 0f,
        humidity = rnd.nextInt(54, 90),
        pressure = rnd.nextInt(980, 1034),
        windSpeed = rnd.nextInt(0, 22),
    )

    private fun Place.getCondition() = when (this) {
        Place.NewYork -> Condition.Sunny
        Place.London -> Condition.Fog
        Place.Tokyo -> Condition.Clear
        Place.Sydney -> Condition.Fog
    }

    private fun createRandomCondition(): Condition {
        return Condition.values()[rnd.nextInt(0, Condition.values().size)]
    }

    private fun Place.getDateTime() = when (this) {
        Place.NewYork -> ZonedDateTime.now(ZoneId.of("America/New_York"))
        Place.London -> ZonedDateTime.now(ZoneId.of("Europe/London"))
        Place.Tokyo -> ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
        Place.Sydney -> ZonedDateTime.now(ZoneId.of("Australia/Sydney"))
    }
}
