package com.plcoding.weatherapp.domain.weather

data class WeatherInfo @JvmOverloads constructor(
    val weatherDataPerDay: Map<Int, List<WeatherData>>? = null,
    val currentWeatherData: WeatherData? = null
) // End of WeatherInfo class
