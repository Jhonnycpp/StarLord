package br.com.jhonny.starlord.ui.navigation

import kotlinx.serialization.Serializable

public sealed interface Route {
    @Serializable
    public data object Home : Route

    @Serializable
    public data class Detail(val id: Int) : Route
}
