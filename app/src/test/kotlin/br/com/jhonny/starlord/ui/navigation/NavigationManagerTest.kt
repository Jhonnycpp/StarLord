package br.com.jhonny.starlord.ui.navigation

import app.cash.turbine.test
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NavigationManagerTest {
    @get:Rule
    val scope = TestCoroutineScopeRule()
    private val navigation = NavigationManager()

    @Test
    fun `navigate should update state with Navigate command`() = runTest {
        navigation.state.test {
            assertEquals(NavigationCommand.Idle, awaitItem())

            navigation.navigate(Route.Home)

            assertEquals(NavigationCommand.Navigate(Route.Home), awaitItem())
        }
    }

    @Test
    fun `back should update state with Back command`() = runTest {
        navigation.state.test {
            assertEquals(NavigationCommand.Idle, awaitItem())

            navigation.back()

            assertEquals(NavigationCommand.Back, awaitItem())
        }
    }

    @Test
    fun `popUpTo should update state with PopUpTo command`() = runTest {
        navigation.state.test {
            assertEquals(NavigationCommand.Idle, awaitItem())

            navigation.popUpTo(Route.Home)

            assertEquals(NavigationCommand.PopUpTo(Route.Home, true), awaitItem())
        }
    }

    @Test
    fun `moveToIdle should update state with Idle command`() = runTest {
        navigation.state.test {
            assertEquals(NavigationCommand.Idle, awaitItem())

            navigation.navigate(Route.Home)

            assertEquals(NavigationCommand.Navigate(Route.Home), awaitItem())

            navigation.moveToIdle()

            assertEquals(NavigationCommand.Idle, awaitItem())
        }
    }
}
