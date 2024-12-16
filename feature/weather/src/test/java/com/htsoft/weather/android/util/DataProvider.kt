package com.htsoft.weather.android.util

import com.htsoft.weather.android.data.response.ConditionResponse
import com.htsoft.weather.android.data.response.CurrentResponse
import com.htsoft.weather.android.data.response.LocationResponse
import com.htsoft.weather.android.data.response.WeatherResponse
import com.htsoft.weather.android.domain.Condition
import com.htsoft.weather.android.domain.Current
import com.htsoft.weather.android.domain.Location
import com.htsoft.weather.android.domain.Weather

object DataProvider {
    internal val weatherResponse = WeatherResponse(
        location = LocationResponse(
            name = "New York",
            region = "New York",
            country = "USA"
        ),
        current = CurrentResponse(
            tempCelsius = 22.5,
            condition = ConditionResponse(
                text = "Partly Cloudy",
                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
            ),
            humidity = 60,
            feelsLikeCelsius = 21.0,
            uv = 5.0
        )
    )
    internal val weather = Weather(
        location = Location(
            name = "New York",
            region = "New York",
            country = "United States of America"
        ),
        current = Current(
            tempCelsius = 3.3,
            condition = Condition(
                text = "Light rain",
                icon = "https//cdn.weatherapi.com/weather/64x64/night/296.png"
            ),
            humidity = 82,
            feelsLikeCelsius = 0.5,
            uv = 0.0
        )
    )
}