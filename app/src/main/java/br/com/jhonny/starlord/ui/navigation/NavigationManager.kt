package br.com.jhonny.starlord.ui.navigation

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

public class NavigationManager : Navigation {
    private val _state = MutableStateFlow<NavigationCommand>(NavigationCommand.Idle)
    override val state: StateFlow<NavigationCommand> get() = _state.asStateFlow()

    override fun navigate(route: Route) {
        Log.d("NavigationManager", "[navigate: $route][previous: ${_state.value}]")

        _state.update { NavigationCommand.Navigate(route) }
    }

    override fun back() {
        Log.d("NavigationManager", "back [current: ${_state.value}]")

        _state.update { NavigationCommand.Back }
    }

    override fun popUpTo(route: Route) {
        Log.d("NavigationManager", "[popUpTo: $route][previous: ${_state.value}]")

        _state.update { NavigationCommand.PopUpTo(route, true) }
    }

    override fun moveToIdle() {
        _state.update { NavigationCommand.Idle }
    }
}
