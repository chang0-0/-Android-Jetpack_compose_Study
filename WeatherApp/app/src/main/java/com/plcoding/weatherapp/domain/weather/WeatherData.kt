package com.plcoding.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData @JvmOverloads constructor(
    val time: LocalDateTime? = null,
    val temperatureCelsius: Double = 0.0,
    val pressure: Double = 0.0,
    val windSpeed: Double = 0.0,
    val humidity: Double = 0.0,
    val weatherType: WeatherType? = null,
)
