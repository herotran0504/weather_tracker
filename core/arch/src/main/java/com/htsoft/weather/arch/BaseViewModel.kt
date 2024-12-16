package com.htsoft.weather.arch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<T> : ViewModel() {
    private val _state = MutableStateFlow<UiState<T>>(UiState.Idle)
    val state: StateFlow<UiState<T>> = _state.asStateFlow()

    protected fun emitState(state: UiState<T>) {
        _state.value = state
    }
}
