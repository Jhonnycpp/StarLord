package br.com.jhonny.starlord.ui.screen.home.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import br.com.jhonny.starlord.feature.home.usecase.RetrieveRepositoryUseCase
import br.com.jhonny.starlord.rule.TestCoroutineScopeRule
import br.com.jhonny.starlord.ui.navigation.Navigation
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiEvent
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiState
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import io.mockk.coEvery
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import java.util.Date

class DetailViewModelTest {
    @get:Rule
    private val scope = TestCoroutineScopeRule()
    private val navigation = mockk<Navigation>()
    private val retrieveRepositoryUseCase = mockk<RetrieveRepositoryUseCase>()

    @Test
    fun `on ui event Back should successfully call navigation back`() {
        val viewModel = buildViewModel()

        justRun { navigation.back() }

        viewModel.onUiEvent(DetailUiEvent.Back)

        verify { navigation.back() }
    }

    @Test
    fun `on ui event GetRepositoryData should successfully call retrieveRepositoryUseCase`() = runTest {
        val viewModel = buildViewModel("id" to 1)

        coEvery { retrieveRepositoryUseCase(any()) } returns expectedResult

        viewModel.onUiEvent(DetailUiEvent.GetRepositoryData)

        viewModel.uiState.test {
            scope.scheduler.advanceUntilIdle()

            assertEquals(DetailUiState.Uninitialized, awaitItem())

            val result = awaitItem() as? DetailUiState.Loaded
            assertNotNull("Result isn't a DetailUiState.Loaded", result)
            assertEquals(result!!.repository, expectedResult)
        }
    }

    @Test
    fun `on ui event GetRepositoryData should return Error when retrieveRepositoryUseCase returns null`() = runTest {
        val viewModel = buildViewModel("id" to 1)

        coEvery { retrieveRepositoryUseCase(any()) } returns null

        viewModel.onUiEvent(DetailUiEvent.GetRepositoryData)

        viewModel.uiState.test {
            scope.scheduler.advanceUntilIdle()

            assertEquals(DetailUiState.Uninitialized, awaitItem())
            assertEquals(DetailUiState.Error, awaitItem())
        }
    }

    @Test
    fun `should return ui error when failed to retrieve from saved state handle`() = runTest {
        val viewModel = buildViewModel()

        viewModel.onUiEvent(DetailUiEvent.GetRepositoryData)

        viewModel.uiState.test {
            scope.scheduler.advanceUntilIdle()

            assertEquals(DetailUiState.Uninitialized, awaitItem())
            assertEquals(DetailUiState.Error, awaitItem())
        }
    }

    private fun buildViewModel(
        vararg input: Pair<String, Any?>,
    ): DetailViewModel = DetailViewModel(
        savedStateHandle = SavedStateHandle(input.toMap()),
        navigation = navigation,
        retrieveRepositoryUseCase = retrieveRepositoryUseCase,
        dispatcher = scope.dispatcher,
    )

    private companion object {
        val expectedResult = RepositoryVO(
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
    }
}
