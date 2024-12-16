package com.htsoft.weather.android.data.repository

import com.htsoft.weather.android.api.WeatherApi
import com.htsoft.weather.android.data.response.toModel
import com.htsoft.weather.android.domain.Weather
import com.htsoft.weather.android.provider.ApiKeyProvider
import javax.inject.Inject

interface WeatherRepository {
    suspend fun fetchWeather(city: String): Result<Weather>
}

internal class WeatherRepositoryImpl @Inject constructor(
    private val apiKeyProvider: ApiKeyProvider,
    private val weatherApi: WeatherApi
) : WeatherRepository {

    private val apiKey get() = apiKeyProvider.getApiKey()

    override suspend fun fetchWeather(city: String) = try {
        val weatherResponse = weatherApi.getWeather(apiKey, city)
        Result.success(weatherResponse.toModel())
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}
