package ru.itis.homework7.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ru.itis.homework7.auth.domain.AuthUseCase
import ru.itis.homework7.auth.domain.model.User
import ru.itis.homework7.data.doOnError
import ru.itis.homework7.data.doOnSuccess
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _authSharedFlow = MutableSharedFlow<Boolean>()
    val authSharedFlow: SharedFlow<Boolean> = _authSharedFlow

    fun auth(username: String, password: String) {
        viewModelScope.launch {
            authUseCase.auth(User(username, password))
                .doOnSuccess { _authSharedFlow.emit(true) }
                .doOnError { _authSharedFlow.emit(false) }
        }
    }


}