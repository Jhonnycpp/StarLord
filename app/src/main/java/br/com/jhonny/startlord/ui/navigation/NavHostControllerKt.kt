package br.com.jhonny.startlord.ui.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import br.com.jhonny.startlord.ui.screen.home.detail.DetailScreenStateOwner
import br.com.jhonny.startlord.ui.screen.home.list.HomeScreeStateOwner


public fun NavHostController.createNavGraph(): NavGraph = createGraph(
    startDestination = Route.Home,
) {
    composable<Route.Home> {
        HomeScreeStateOwner()
    }

    composable<Route.Detail> {
        DetailScreenStateOwner()
    }
}
