package br.com.jhonny.startlord.ui.navigation

import kotlinx.serialization.Serializable

public sealed interface Route {
    @Serializable
    public data object Home : Route

    @Serializable
    public data class Detail(val id: Int) : Route
}
