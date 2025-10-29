package br.com.jhonny.starlord.ui.navigation

/**
 * Interface that represents navigation commands.
 *
 * This interface is used to define the different types of navigation commands that can be used in the application.
 *
 * The following commands are available:
 * - [Back]: Navigates back to the previous screen.
 * - [Navigate]: Navigates to a specific screen.
 * - [PopUpTo]: Pops up to a specific screen in the back stack.
 * - [Idle]: Represents an idle state, where no navigation is occurring.
 */
public sealed interface NavigationCommand {
    /**
     * Represents a command to navigate back to the previous screen.
     */
    public object Back : NavigationCommand
    /**
     * Represents a navigation command to navigate to a specific [Route].
     *
     * @property route The destination [Route] for the navigation.
     */
    public data class Navigate(val route: Route) : NavigationCommand
    /**
     * Represents a navigation command to pop up to a specific route in the back stack.
     *
     * @property route The destination [Route] to pop up to.
     * @property inclusive Whether the specified route should also be popped from the back stack.
     *                     If `true`, the `route` itself is removed.
     *                     If `false`, the `route` becomes the current top destination.
     */
    public data class PopUpTo(val route: Route, val inclusive: Boolean) : NavigationCommand

    /**
     * A [NavigationCommand] that represents the absence of a navigation action.
     * This is useful as a default state or when no navigation is required.
     */
    public data object Idle : NavigationCommand
}
