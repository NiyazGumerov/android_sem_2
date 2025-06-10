package ru.itis.homework7.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.itis.homework7.data.doOnError
import ru.itis.homework7.data.doOnSuccess
import ru.itis.homework7.domain.GetActualCourseUseCase
import ru.itis.homework7.domain.GetCourseByDateUseCase
import ru.itis.homework7.domain.model.Rates
import ru.itis.homework7.presentation.model.RatesState
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val getActualCourseUseCase: GetActualCourseUseCase,
    private val getCourseByDateUseCase: GetCourseByDateUseCase,
) : ViewModel() {

    private var _ratesState = MutableStateFlow<RatesState>(RatesState.Init)
    val ratesState: StateFlow<RatesState> = _ratesState

    private val _sourceSharedFlow = MutableSharedFlow<Rates.Source?>()
    val sourceSharedFlow: SharedFlow<Rates.Source?> = _sourceSharedFlow

    init {
        getActualCourse()
    }

    fun getCourseByDateUseCase(date: String) {
        Log.d("", date)
        viewModelScope.launch {
            _ratesState.value = RatesState.Loading
            getCourseByDateUseCase.getCourseByDate(date)
                .doOnSuccess {
                    _ratesState.value = RatesState.Success(it)
                    _sourceSharedFlow.emit(it.source)
                }.doOnError {
                    _ratesState.value = RatesState.Error
                }
        }
    }

    fun getActualCourse() {
        viewModelScope.launch {
            _ratesState.value = RatesState.Loading
            getActualCourseUseCase.getActualCourse().doOnSuccess {
                _ratesState.value = RatesState.Success(it)
            }.doOnError {
                _ratesState.value = RatesState.Error
            }
        }
    }
}