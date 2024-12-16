package com.htsoft.weather.android.data.response

import com.google.gson.annotations.SerializedName

internal data class WeatherResponse(
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("current")
    val current: CurrentResponse
)

internal data class LocationResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("country")
    val country: String
)

internal data class CurrentResponse(
    @SerializedName("temp_c")
    val tempCelsius: Double,
    @SerializedName("condition")
    val condition: ConditionResponse,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("feels_like_c")
    val feelsLikeCelsius: Double,
    @SerializedName("uv")
    val uv: Double
)

internal data class ConditionResponse(
    @SerializedName("text")
    val text: String,
    @SerializedName("icon")
    val icon: String
)
