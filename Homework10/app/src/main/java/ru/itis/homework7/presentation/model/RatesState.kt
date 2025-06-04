package ru.itis.homework7.presentation.model

import ru.itis.homework7.domain.model.Rates

sealed class RatesState {
    data object Init: RatesState()
    data object Loading : RatesState()
    data class Success(val rates: Rates) : RatesState()
    data object Error: RatesState()
}