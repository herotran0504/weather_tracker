package com.htsoft.weather.android.ui.components

import com.htsoft.weather.android.domain.Condition
import com.htsoft.weather.android.domain.Current
import com.htsoft.weather.android.domain.Location
import com.htsoft.weather.android.domain.Weather

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