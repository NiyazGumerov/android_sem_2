package ru.itis.homework7.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.itis.homework7.data.doOnSuccess
import ru.itis.homework7.domain.GetUserIdUseCase
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    private var _startDestinationState = MutableStateFlow<Boolean?>(null)
    val startDestinationState: StateFlow<Boolean?> = _startDestinationState

    init {
        viewModelScope.launch {
            getUserIdUseCase.getUserId().doOnSuccess {
                _startDestinationState.value = it
            }
        }
    }

}