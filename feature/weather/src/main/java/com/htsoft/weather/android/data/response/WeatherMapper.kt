package com.htsoft.weather.android.data.response

import com.htsoft.weather.android.domain.Condition
import com.htsoft.weather.android.domain.Current
import com.htsoft.weather.android.domain.Location
import com.htsoft.weather.android.domain.Weather

internal fun WeatherResponse.toModel() = Weather(
    location = location.toModel(),
    current = current.toModel(),
)

internal fun LocationResponse.toModel() = Location(
    name = name,
    region = region,
    country = country
)

internal fun CurrentResponse.toModel() = Current(
    tempCelsius = tempCelsius,
    condition = condition.toModel(),
    humidity = humidity,
    feelsLikeCelsius = feelsLikeCelsius,
    uv = uv,
)

internal fun ConditionResponse.toModel() = Condition(
    text = text,
    icon = "https:$icon"
)
