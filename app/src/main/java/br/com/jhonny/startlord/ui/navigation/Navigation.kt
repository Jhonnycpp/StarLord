package br.com.jhonny.startlord.ui.navigation

import kotlinx.coroutines.flow.StateFlow

public interface Navigation {
    public val state: StateFlow<NavigationCommand>
    public fun navigate(route: Route)
    public fun back()
    public fun popUpTo(route: Route)

    public fun moveToIdle()
}
