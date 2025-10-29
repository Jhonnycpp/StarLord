package br.com.jhonny.starlord.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import br.com.jhonny.starlord.ui.AppContent
import br.com.jhonny.starlord.ui.navigation.Navigation
import br.com.jhonny.starlord.ui.navigation.NavigationCommand
import br.com.jhonny.starlord.ui.navigation.Route
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val navigation = mockk<Navigation>()
    private val state = MutableStateFlow<NavigationCommand>(NavigationCommand.Idle)

    private val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    ).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
    }

    private val navGraph = navController.createGraph(startDestination = Route.Home) {
        composable<Route.Home> { Text("Home") }
        composable<Route.Detail> { Text("Detail") }
    }

    @Before
    fun setUp() {
        justRun { navigation.back() }
        justRun { navigation.moveToIdle() }
        every { navigation.state } returns state
    }

    @Test
    fun should_Create_AppNavigation_And_Navigate_To_Home() {
        setScreenContent()

        state.update { NavigationCommand.Navigate(Route.Home) }

        composeTestRule.waitForIdle()

        assertEquals(Route.Home::class.qualifiedName, navController.currentBackStackEntry?.destination?.route)

        verify { navigation.moveToIdle() }
    }

    @Test
    fun should_Create_AppNavigation_And_Navigate_To_Detail() {
        val expectedRoute = "${Route.Detail::class.qualifiedName}/{id}"

        setScreenContent()

        state.update { NavigationCommand.Navigate(Route.Detail(1)) }

        composeTestRule.waitForIdle()

        assertEquals(expectedRoute, navController.currentBackStackEntry?.destination?.route)

        verify { navigation.moveToIdle() }
    }

    @Test
    fun should_Create_AppNavigation_Back_To_Previous() {
        val expectedRoute = "${Route.Detail::class.qualifiedName}/{id}"

        setScreenContent()

        state.update { NavigationCommand.Navigate(Route.Detail(1)) }

        composeTestRule.waitForIdle()

        assertEquals(expectedRoute, navController.currentBackStackEntry?.destination?.route)

        state.update { NavigationCommand.Back }

        composeTestRule.waitForIdle()

        assertEquals(Route.Home::class.qualifiedName, navController.currentBackStackEntry?.destination?.route)

        verify { navigation.moveToIdle() }
    }

    @Test
    fun should_Create_AppNavigation_And_Navigate_To_Detail_And_Pop_Up_To_Home() {
        setScreenContent()
        repeat(3) { count ->
            state.update { NavigationCommand.Navigate(Route.Detail(count)) }
            composeTestRule.waitForIdle()
        }

        state.update { NavigationCommand.PopUpTo(Route.Home, false) }

        composeTestRule.waitForIdle()

        assertEquals(Route.Home::class.qualifiedName, navController.currentBackStackEntry?.destination?.route)

        verify { navigation.moveToIdle() }
    }

    @Test
    fun should_Create_AppNavigation_And_BackHandler_triggersNavigationManagerBack() {
        val expectedRoute = "${Route.Detail::class.qualifiedName}/{id}"
        setScreenContent()

        state.update { NavigationCommand.Navigate(Route.Detail(1)) }

        composeTestRule.waitForIdle()

        assertEquals(expectedRoute, navController.currentBackStackEntry?.destination?.route)

        val activity = composeTestRule.activity

        activity.onBackPressedDispatcher.onBackPressed()

        composeTestRule.waitForIdle()

        verify { navigation.moveToIdle() }
    }

    @Test
    fun should_Create_AppNavigation_OnIdle() {
        setScreenContent()

        composeTestRule.waitForIdle()

        verify(exactly = 0) {
            navigation.back()
            navigation.moveToIdle()
        }
    }

    private fun setScreenContent() {
        composeTestRule.setContent {
            AppContent(
                navController = navController,
                navGraph = navGraph,
                navigationManager = navigation,
            )
        }
    }
}
