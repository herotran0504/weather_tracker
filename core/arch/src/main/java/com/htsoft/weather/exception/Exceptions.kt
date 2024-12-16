package com.htsoft.weather.exception

class NoNetworkException(
    message: String = "No internet connection available"
) : Exception(message)

class WeatherApiKeyMissingException(
    message: String = "Weather API key is not configured. Please set up your API key to use the app."
) : Exception(message)
