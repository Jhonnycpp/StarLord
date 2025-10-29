package br.com.jhonny.starlord.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Represents the different routes in the application.
 * This sealed interface is used for navigation purposes,
 * allowing type-safe routing and easy serialization for passing arguments.
 *
 * Each object or data class implementing this interface represents a distinct screen
 * or destination within the app.
 */
public sealed interface Route {
    /**
     * Represents the home screen of the application.
     */
    @Serializable
    public data object Home : Route

    /**
     * Represents the detail screen route.
     *
     * @property id The unique identifier for the item to be displayed in detail.
     */
    @Serializable
    public data class Detail(val id: Int) : Route
}
