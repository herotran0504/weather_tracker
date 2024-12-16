package com.htsoft.weather.android.provider

import com.htsoft.weather.android.BuildConfig
import javax.inject.Inject

class DefaultApiKeyProvider @Inject constructor() : ApiKeyProvider {

    override fun getApiKey() = BuildConfig.WEATHER_API_KEY

}
