package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto @JvmOverloads constructor(
    val time: List<String>? = emptyList(),
    @field: Json(name = "temperature_2m")
    val temperatures: List<Double>? = emptyList(),
    @field : Json(name = "weathercode")
    val weatherCodes: List<Int>? = emptyList(),
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>? = emptyList(),
    @field:Json(name = "windspeed_10")
    val windSpeeds: List<Double>? = emptyList(),
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>? = emptyList()
) // End of WeatherDataDto class
