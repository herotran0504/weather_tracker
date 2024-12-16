package com.htsoft.weather.android.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.htsoft.weather.R
import com.htsoft.weather.android.domain.Weather
import com.htsoft.weather.android.ui.annotation.LightPreview
import com.htsoft.weather.android.viewmodel.WeatherViewModel
import com.htsoft.weather.arch.UiState
import com.htsoft.weather.exception.NoNetworkException
import com.htsoft.weather.exception.WeatherApiKeyMissingException

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSavedCity()
    }

    WeatherScreenContent(
        uiState = uiState, onSearch = {
            viewModel.fetchWeatherForCity(it)
        }, onViewDetails = {
            viewModel.saveCity(it)
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WeatherScreenContent(
    uiState: UiState<Weather>,
    onSearch: (String) -> Unit,
    onViewDetails: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var searchDetails by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            placeHolder = stringResource(R.string.weather_search_location),
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = {
                if (searchQuery.isNotBlank()) {
                    onSearch(searchQuery)
                    searchDetails = true
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            },
        )
        when (uiState) {
            is UiState.Error -> HandleError(uiState)
            UiState.Idle -> WeatherEmptyScreen()
            UiState.Loading -> {}
            is UiState.Success<Weather> -> {
                SharedTransitionLayout {
                    AnimatedContent(
                        searchDetails,
                        label = "basic_transition"
                    ) { targetState ->
                        if (targetState) {
                            WeatherSearchResult(
                                weather = uiState.data,
                                onClick = {
                                    onViewDetails(searchQuery)
                                    searchDetails = false
                                },
                                this@SharedTransitionLayout,
                                animatedContentScope = this@AnimatedContent,
                            )
                        } else {
                            WeatherDetailsScreen(
                                weather = uiState.data,
                                this@SharedTransitionLayout,
                                animatedContentScope = this@AnimatedContent,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HandleError(error: UiState.Error) {
    if (error.throwable is NoNetworkException) {
        WeatherErrorScreen(Icons.Default.WifiOff, error.throwable.message.orEmpty())
    } else if (error.throwable is WeatherApiKeyMissingException) {
        WeatherErrorScreen(Icons.Default.Error, error.throwable.message.orEmpty())
    }
}

@LightPreview
@Composable
fun WeatherScreenPreview() {
    Column {
        val onSearch: (String) -> Unit = { }
        val onViewDetails: (String) -> Unit = { }
        WeatherScreenContent(UiState.Idle, onSearch, onViewDetails)
        WeatherScreenContent(UiState.Loading, onSearch, onViewDetails)
        WeatherScreenContent(UiState.Success(weather), onSearch, onViewDetails)
        WeatherScreenContent(UiState.Error(NoNetworkException()), onSearch, onViewDetails)
    }
}
