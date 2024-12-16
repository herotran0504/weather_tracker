package com.htsoft.weather.android.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CityRepository {
    suspend fun getSavedCity(): String?
    suspend fun saveCity(cityName: String)
    suspend fun removeCity()
}

private val CITY_KEY = stringPreferencesKey("saved_city")

internal val Context.dataStore by preferencesDataStore(name = "city_prefs")

class CityRepositoryImpl @Inject constructor(
    private val context: Context
) : CityRepository {

    override suspend fun getSavedCity(): String? = context.dataStore.data
        .map { it[CITY_KEY] }
        .firstOrNull()

    override suspend fun saveCity(cityName: String) {
        context.dataStore.edit {
            it[CITY_KEY] = cityName
        }
    }

    override suspend fun removeCity() {
        context.dataStore.edit {
            it.remove(CITY_KEY)
        }
    }
}
