package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDto @JvmOverloads constructor(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto? = WeatherDataDto()
) // End of WeatherDto class