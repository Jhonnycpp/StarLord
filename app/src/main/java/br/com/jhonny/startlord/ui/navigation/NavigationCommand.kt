package br.com.jhonny.startlord.ui.navigation

public sealed interface NavigationCommand {
    public object Back : NavigationCommand
    public data class Navigate(val route: Route) : NavigationCommand
    public data class PopUpTo(val route: Route, val inclusive: Boolean) : NavigationCommand

    public data object Idle : NavigationCommand
}
