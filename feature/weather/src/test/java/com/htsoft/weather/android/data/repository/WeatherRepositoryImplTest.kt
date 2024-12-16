package com.htsoft.weather.android.data.repository

import com.htsoft.weather.android.api.WeatherApi
import com.htsoft.weather.android.domain.Weather
import com.htsoft.weather.android.provider.ApiKeyProvider
import com.htsoft.weather.android.util.DataProvider.weatherResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    private val apiKeyProvider: ApiKeyProvider = mockk()
    private val weatherApi: WeatherApi = mockk()

    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        weatherRepository = WeatherRepositoryImpl(apiKeyProvider, weatherApi)
    }

    @Test
    fun `test fetchWeather returns success`() = runTest {
        val city = "New York"
        val mockApiKey = "mock-api-key"

        every { apiKeyProvider.getApiKey() } returns mockApiKey

        coEvery { weatherApi.getWeather(mockApiKey, any()) } returns weatherResponse

        val result: Result<Weather> = weatherRepository.fetchWeather(city)

        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
    }

    @Test
    fun `test fetchWeather returns failure when exception occurs`() = runTest {
        val city = "New York"
        val mockApiKey = "mock-api-key"

        every { apiKeyProvider.getApiKey() } returns mockApiKey

        coEvery { weatherApi.getWeather(mockApiKey, city) } throws Exception("API error")

        val result = weatherRepository.fetchWeather(city)

        assertTrue(result.isFailure)
        assertEquals("API error", result.exceptionOrNull()?.message)
    }
}

