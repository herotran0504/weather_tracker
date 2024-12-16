package com.htsoft.weather.android.di

import android.app.Application
import android.content.Context
import com.htsoft.weather.android.provider.ApiKeyProvider
import com.htsoft.weather.android.provider.DefaultApiKeyProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindContext(application: Application): Context

    @Binds
    @Singleton
    fun bindApiKeyProvider(provider: DefaultApiKeyProvider): ApiKeyProvider
}
