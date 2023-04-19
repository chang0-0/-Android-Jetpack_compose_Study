package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDto constructor(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto?
) // End of WeatherDto class