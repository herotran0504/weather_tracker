package com.htsoft.weather.android.api

import com.htsoft.weather.android.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApi {

    @GET("current.json")
    suspend fun getWeather(@Query("key") apiKey: String, @Query("q") city: String): WeatherResponse

}
