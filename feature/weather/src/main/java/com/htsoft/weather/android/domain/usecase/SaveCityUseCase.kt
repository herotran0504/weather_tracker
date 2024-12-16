package com.htsoft.weather.android.domain.usecase

import com.htsoft.weather.android.data.repository.CityRepository
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend fun execute(city: String) {
        cityRepository.saveCity(city)
    }

}
