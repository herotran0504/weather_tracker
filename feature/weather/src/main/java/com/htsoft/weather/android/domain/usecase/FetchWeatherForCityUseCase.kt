package com.htsoft.weather.android.domain.usecase

import com.htsoft.weather.android.data.repository.WeatherRepository
import com.htsoft.weather.android.domain.Weather
import javax.inject.Inject

class FetchWeatherForCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun execute(cityName: String): Result<Weather> {
        return weatherRepository.fetchWeather(cityName)
    }
}
