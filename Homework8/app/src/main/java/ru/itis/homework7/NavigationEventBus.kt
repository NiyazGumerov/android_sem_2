package ru.itis.homework7

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.itis.homework7.navigation.Route
import javax.inject.Singleton

@Singleton
object NavigationEventBus {
    private val _events = MutableSharedFlow<Route>()
    val events: SharedFlow<Route> = _events

    suspend fun navigateTo(route: Route) {
        _events.emit(route)
    }
}

