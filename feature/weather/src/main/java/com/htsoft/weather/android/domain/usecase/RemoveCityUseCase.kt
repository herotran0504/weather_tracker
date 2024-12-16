package com.htsoft.weather.android.domain.usecase

import com.htsoft.weather.android.data.repository.CityRepository
import javax.inject.Inject

class RemoveCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    suspend fun execute() {
        cityRepository.removeCity()
    }
}
