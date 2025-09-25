package br.com.jhonny.starlord.ui.screen.home.list

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiEvent
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiState
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel = mockk<HomeViewModel>()
    private val uiState = MutableStateFlow<HomeUiState>(HomeUiState.Uninitialized)

    @Before
    fun setUp() {
        every { viewModel.uiState } returns uiState
        justRun { viewModel.onUiEvent(any()) }
    }

    @Test
    fun should_Successfully_ShowHomeScreen_With6Elements() {
        val repositories = buildRepositories(6)

        uiState.update { HomeUiState.Loaded(repositories = repositories) }

        setScreenContent()

        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true).assertCountEquals(6)
    }

    @Test
    fun should_Successfully_ShowHomeScreen_With20Elements_And_LoadMoreWasCalled() {
        val repositories = buildRepositories(20)

        uiState.update { HomeUiState.Loaded(repositories = repositories) }

        setScreenContent()

        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("GitRepositoryList").performScrollToIndex(repositories.size - 1)

        composeTestRule.waitForIdle()

        verify { viewModel.onUiEvent(HomeUiEvent.RequestMoreData) }
    }

    @Test
    fun should_Successfully_TriggerOnItemClick_And_ShowDetails() {
        val repositories = buildRepositories(6)

        uiState.update { HomeUiState.Loaded(repositories = repositories) }

        setScreenContent()

        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true).assertCountEquals(6)

        val itemsTree = composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true)
        val firstItem = itemsTree[0]

        firstItem.assertIsDisplayed()
        firstItem.performClick()

        verify { viewModel.onUiEvent(HomeUiEvent.ShowRepositoryInfo(1)) }
    }

    @Test
    fun should_Successfully_ShowProgressMessage_OnLoadingState() {
        setScreenContent()

        uiState.update { HomeUiState.Loading }

        composeTestRule.onNodeWithTag("HomeScreenLoading", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreenLoading", useUnmergedTree = true).assertIsDisplayed()

        verify(exactly = 1) { viewModel.onUiEvent(HomeUiEvent.RequestMoreData) }
    }

    @Test
    fun should_Successfully_ShowProgressMessage_OnUninitializedState() {
        setScreenContent()

        composeTestRule.onNodeWithTag("HomeScreenLoading", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreenLoading", useUnmergedTree = true).assertIsDisplayed()

        verify(exactly = 1) { viewModel.onUiEvent(HomeUiEvent.RequestMoreData) }
    }

    @Test
    fun should_Successfully_ShowErrorMessage_OnErrorState() {
        uiState.update { HomeUiState.Error }

        setScreenContent()

        composeTestRule.onNodeWithTag("HomeScreenError", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreenError", useUnmergedTree = true).assertIsDisplayed()
    }

    private fun setScreenContent() {
        composeTestRule.setContent {
            HomeScreeStateOwner(
                viewModel = viewModel,
            )
        }
    }

    private fun buildRepositories(length: Int) = (1..length).map {
        RepositoryVO(
            id = it,
            name = "name $it",
            description = "description $it",
            author = "author $it",
            userAvatar = "avatar $it",
            language = "language $it",
            licenseName = "Apache License 2.0 $it",
            createdAt = Date(0),
            updatedAt = Date(0),
            pushedAt = Date(0),
            watcherCount = it,
            issueCount = it,
            starCount = it,
            forkCount = it,
        )
    }
}
