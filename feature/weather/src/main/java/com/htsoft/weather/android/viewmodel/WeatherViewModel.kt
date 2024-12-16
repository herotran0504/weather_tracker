package com.htsoft.weather.android.viewmodel

import androidx.lifecycle.viewModelScope
import com.htsoft.weather.android.domain.Weather
import com.htsoft.weather.android.domain.usecase.FetchWeatherForCityUseCase
import com.htsoft.weather.android.domain.usecase.GetSavedCityUseCase
import com.htsoft.weather.android.domain.usecase.RemoveCityUseCase
import com.htsoft.weather.android.domain.usecase.SaveCityUseCase
import com.htsoft.weather.android.provider.ApiKeyProvider
import com.htsoft.weather.arch.BaseViewModel
import com.htsoft.weather.arch.UiState
import com.htsoft.weather.exception.NoNetworkException
import com.htsoft.weather.exception.WeatherApiKeyMissingException
import com.htsoft.weather.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getSavedCityUseCase: GetSavedCityUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val removeCityUseCase: RemoveCityUseCase,
    private val fetchWeatherForCityUseCase: FetchWeatherForCityUseCase,
    private val apiKeyProvider: ApiKeyProvider,
    private val networkUtils: NetworkUtils
) : BaseViewModel<Weather>() {

    fun loadSavedCity() {
        viewModelScope.launch {
            getSavedCityUseCase.execute()?.let {
                fetchWeatherForCity(it)
            }
        }
    }

    fun fetchWeatherForCity(cityName: String) {
        viewModelScope.launch {
            try {
                emitState(UiState.Loading)

                when {
                    apiKeyProvider.getApiKey().isEmpty() -> emitState(UiState.Error(WeatherApiKeyMissingException()))

                    !networkUtils.isNetworkAvailable() -> emitState(UiState.Error(NoNetworkException()))

                    else -> fetchWeatherForCityUseCase.execute(cityName).onSuccess {
                        emitState(UiState.Success(it))
                    }.onFailure {
                        emitState(UiState.Error(it))
                        removeCity()
                    }
                }
            } catch (e: Exception) {
                emitState(UiState.Error(e))
            }
        }
    }

    fun saveCity(cityName: String) {
        viewModelScope.launch {
            saveCityUseCase.execute(cityName)
        }
    }

    private fun removeCity() {
        viewModelScope.launch {
            removeCityUseCase.execute()
        }
    }

}
