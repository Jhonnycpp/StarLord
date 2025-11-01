package br.com.jhonny.starlord.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.jhonny.starlord.ui.navigation.Navigation
import br.com.jhonny.starlord.ui.navigation.NavigationCommand
import org.koin.compose.koinInject

@Composable
public fun AppContent(
    navController: NavHostController,
    navGraph: NavGraph,
    navigationManager: Navigation = koinInject(),
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navigationState by navigationManager.state.collectAsState()

        NavHost(
            navController = navController,
            graph = navGraph,
            enterTransition = { fadeIn(animationSpec = tween(700)) + slideInHorizontally() },
            exitTransition = { fadeOut(animationSpec = tween(700)) + slideOutHorizontally() },
            popEnterTransition = { fadeIn(animationSpec = tween(700)) + slideInHorizontally(initialOffsetX = { -it }) },
            popExitTransition = { fadeOut(animationSpec = tween(700)) + slideOutHorizontally(targetOffsetX = { -it }) },
            modifier = Modifier.padding(
                paddingValues = innerPadding
            ),
        )

        when (val state = navigationState) {
            NavigationCommand.Back -> {
                navController.popBackStack()
                navigationManager.moveToIdle()
            }

            is NavigationCommand.Navigate -> {
                navController.navigate(state.route)
                navigationManager.moveToIdle()
            }

            is NavigationCommand.PopUpTo -> {
                navController.popBackStack(state.route, state.inclusive)
                navigationManager.moveToIdle()
            }

            NavigationCommand.Idle -> Unit
        }

        BackHandler(
            enabled = navController.previousBackStackEntry != null,
        ) {
            navigationManager.back()
        }
    }
}
