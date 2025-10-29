package br.com.jhonny.starlord.ui.navigation

import kotlinx.coroutines.flow.StateFlow

/**
 * Interface defining navigation actions within the application.
 *
 * This interface provides methods for navigating between different screens or routes,
 * managing the navigation back stack, and observing navigation commands.
 */
public interface Navigation {
    /**
     * A [StateFlow] that emits [NavigationCommand] objects to observe and react to navigation events.
     * Clients can collect this flow to be notified when navigation actions occur, such as navigating to a new route,
     * going back, or popping up to a specific route.
     * It also emits an `Idle` state when no navigation is actively being processed.
     */
    public val state: StateFlow<NavigationCommand>
    /**
     * Navigates to the specified route.
     *
     * @param route The destination route to navigate to.
     */
    public fun navigate(route: Route)
    /**
     * Navigates back to the previous screen in the navigation stack.
     */
    public fun back()
    /**
     * Pops up to a given route, removing all intermediate screens from the back stack.
     *
     * @param route The destination route to pop up to.
     */
    public fun popUpTo(route: Route)

    /**
     * Resets the navigation state to idle.
     * This is useful to prevent the navigation from being triggered multiple times, for example,
     * when the user clicks a button multiple times quickly.
     */
    public fun moveToIdle()
}
