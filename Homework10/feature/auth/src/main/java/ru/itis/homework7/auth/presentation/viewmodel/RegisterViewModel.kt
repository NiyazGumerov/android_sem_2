package ru.itis.homework7.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.itis.homework7.auth.domain.RegisterUseCase
import ru.itis.homework7.auth.domain.model.User
import ru.itis.homework7.data.doOnError
import ru.itis.homework7.data.doOnSuccess
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerSharedFlow = MutableSharedFlow<Boolean>()
    val registerSharedFlow: SharedFlow<Boolean> = _registerSharedFlow

    fun register(username: String, password: String) {
        viewModelScope.launch {
            registerUseCase.register(User(username, password))
                .doOnSuccess { _registerSharedFlow.emit(true) }
                .doOnError { _registerSharedFlow.emit(false) }
        }
    }
}