package br.com.jhonny.startlord.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.jhonny.startlord.ui.navigation.Navigation
import br.com.jhonny.startlord.ui.navigation.NavigationCommand
import br.com.jhonny.startlord.ui.navigation.createNavGraph
import br.com.jhonny.startlord.ui.theme.StartLordTheme
import org.koin.compose.koinInject

public class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StartLordTheme {
                val navController: NavHostController = rememberNavController()
                val navGraph = remember { navController.createNavGraph() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navigationManager: Navigation = koinInject()
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

                    with(navController) {
                        val previousRoute = previousBackStackEntry?.destination?.route
                        val currentRoute = currentBackStackEntry?.destination?.route

                        Log.d("MainActivity", "Previous: $previousRoute Current: $currentRoute")
                    }

                    BackHandler {
                        navigationManager.back()
                    }
                }
            }
        }
    }
}
