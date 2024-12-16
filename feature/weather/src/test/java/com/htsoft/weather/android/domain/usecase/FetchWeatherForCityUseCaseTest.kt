package com.htsoft.weather.android.domain.usecase

import com.htsoft.weather.android.data.repository.WeatherRepository
import com.htsoft.weather.android.util.DataProvider.weather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class FetchWeatherForCityUseCaseTest {

    private lateinit var fetchWeatherForCityUseCase: FetchWeatherForCityUseCase
    private val mockWeatherRepository: WeatherRepository = mockk()

    @Before
    fun setup() {
        // Initialize the use case with the mocked repository
        fetchWeatherForCityUseCase = FetchWeatherForCityUseCase(mockWeatherRepository)
    }

    @Test
    fun `execute returns success when repository fetchWeather is successful`() = runTest {
        // Given
        val cityName = "New York"

        coEvery { mockWeatherRepository.fetchWeather(cityName) } returns Result.success(weather)

        // When
        val result = fetchWeatherForCityUseCase.execute(cityName)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(weather, result.getOrNull())
    }

    @Test
    fun `execute returns failure when repository fetchWeather fails`() = runTest {
        // Given
        val cityName = "New York"
        val exception = Exception("Network error")
        coEvery { mockWeatherRepository.fetchWeather(cityName) } returns Result.failure(exception)

        // When
        val result = fetchWeatherForCityUseCase.execute(cityName)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
