package br.com.jhonny.starlord.ui.screen.home.list

import app.cash.turbine.test
import br.com.jhonny.starlord.feature.home.usecase.RetrieveGitHubRepositoryUseCase
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import br.com.jhonny.starlord.ui.navigation.Navigation
import br.com.jhonny.starlord.ui.navigation.Route
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiEvent
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiState
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import java.util.Date

class HomeViewModelTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()
    private val retrieveGitHubRepositoryUseCase = mockk<RetrieveGitHubRepositoryUseCase>()
    private val navigation = mockk<Navigation>()
    private val viewModel = HomeViewModel(
        retrieveGitHubRepositoryUseCase = retrieveGitHubRepositoryUseCase,
        navigation = navigation,
        dispatcher = scope.dispatcher,
    )

    @Test
    fun `on ui event RequestMoreData should successfully call retrieveGitHubRepositoryUseCase`() = runTest {
        coEvery { retrieveGitHubRepositoryUseCase() } returns expectedResult

        viewModel.onUiEvent(HomeUiEvent.RequestMoreData)

        viewModel.uiState.test {
            scope.scheduler.advanceUntilIdle()

            assertEquals(HomeUiState.Uninitialized, awaitItem())

            val result = awaitItem() as? HomeUiState.Loaded
            assertNotNull("Result isn't a HomeUiState.Loaded", result)
            assertEquals(result!!.repositories, expectedResult)
        }
    }

    @Test
    fun `on multiple ui event RequestMoreData should successfully perform one call to retrieveGitHubRepositoryUseCase`() = runTest {
        coEvery { retrieveGitHubRepositoryUseCase() } coAnswers {
            delay(100)
            expectedResult
        }

        viewModel.uiState.test {
            repeat(5) {
                viewModel.onUiEvent(HomeUiEvent.RequestMoreData)
            }

            scope.scheduler.advanceUntilIdle()

            assertEquals(HomeUiState.Uninitialized, awaitItem())

            val result = awaitItem() as? HomeUiState.Loaded
            assertNotNull("Result isn't a HomeUiState.Loaded", result)
            assertEquals(result!!.repositories, expectedResult)
        }

        coVerify(exactly = 1) {
            retrieveGitHubRepositoryUseCase()
        }
    }

    @Test
    fun `on ui event RequestMoreData should return Error when retrieveGitHubRepositoryUseCase returns emptyList`() = runTest {
        coEvery { retrieveGitHubRepositoryUseCase() } returns emptyList()

        viewModel.onUiEvent(HomeUiEvent.RequestMoreData)

        viewModel.uiState.test {
            scope.scheduler.advanceUntilIdle()

            assertEquals(HomeUiState.Uninitialized, awaitItem())
            assertEquals(HomeUiState.Error, awaitItem())
        }
    }

    @Test
    fun `on ui event ShowRepositoryInfo should successfully navigation to detail`() {
        justRun { navigation.navigate(any()) }

        viewModel.onUiEvent(HomeUiEvent.ShowRepositoryInfo(1))

        verify { navigation.navigate(Route.Detail(1)) }
    }

    private companion object {
        val expectedResult = listOf(
            RepositoryVO(
                id = 1,
                name = "name",
                description = "description",
                author = "author",
                userAvatar = "avatar",
                language = "language",
                licenseName = "Apache License 2.0",
                createdAt = Date(0),
                updatedAt = Date(0),
                pushedAt = Date(0),
                watcherCount = 1,
                issueCount = 1,
                starCount = 1,
                forkCount = 1,
            )
        )
    }
}
