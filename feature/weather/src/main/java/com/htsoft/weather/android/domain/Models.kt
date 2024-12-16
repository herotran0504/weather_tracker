package com.htsoft.weather.android.domain

data class Weather(
    val location: Location,
    val current: Current
)

data class Condition(
    val text: String,
    val icon: String
)

data class Current(
    val tempCelsius: Double,
    val condition: Condition,
    val humidity: Int,
    val feelsLikeCelsius: Double,
    val uv: Double
)

data class Location(
    val name: String,
    val region: String,
    val country: String
)
