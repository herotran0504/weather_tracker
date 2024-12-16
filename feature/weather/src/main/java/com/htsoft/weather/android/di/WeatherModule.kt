package com.htsoft.weather.android.di

import com.htsoft.weather.android.api.WeatherApi
import com.htsoft.weather.android.data.repository.CityRepository
import com.htsoft.weather.android.data.repository.CityRepositoryImpl
import com.htsoft.weather.android.data.repository.WeatherRepository
import com.htsoft.weather.android.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface WeatherModule {

    @Binds
    @Singleton
    fun bindCityRepository(repository: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    fun bindWeatherRepository(repository: WeatherRepositoryImpl): WeatherRepository

    companion object {
        @Provides
        @Singleton
        fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
    }
}
