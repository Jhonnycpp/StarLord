package br.com.jhonny.starlord.ui.screen.home.detail

import android.content.res.Configuration
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiEvent
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiState
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

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val viewModel = mockk<DetailViewModel>()
    private val uiState = MutableStateFlow<DetailUiState>(DetailUiState.Uninitialized)

    @Before
    fun setUp() {
        every { viewModel.uiState } returns uiState
        justRun { viewModel.onUiEvent(any()) }
    }

    @Test
    fun should_ShowDetailScreen_OnPortrait() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult) }

        setScreenContent()

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnPortrait_WithoutLanguage() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(language = null)) }

        setScreenContent()

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()

        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnPortrait_WithoutLicense() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(licenseName = null)) }

        setScreenContent()

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()

        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnPortrait_WithEmptyLanguage() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(language = "")) }

        setScreenContent()

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()

        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnPortrait_WithEmptyLicense() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(licenseName = "")) }

        setScreenContent()

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()

        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnLandscape() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult) }

        setScreenContent(Configuration.ORIENTATION_LANDSCAPE)

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnLandscape_WithoutLanguage() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(language = null)) }

        setScreenContent(Configuration.ORIENTATION_LANDSCAPE)

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()

        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnLandscape_WithoutLicense() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(licenseName = null)) }

        setScreenContent(Configuration.ORIENTATION_LANDSCAPE)

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()

        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnLandscape_WithEmptyLanguage() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(language = "")) }

        setScreenContent(Configuration.ORIENTATION_LANDSCAPE)

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()

        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowDetailScreen_OnLandscape_WithEmptyLicense() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult.copy(licenseName = "")) }

        setScreenContent(Configuration.ORIENTATION_LANDSCAPE)

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()

        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsNotDisplayed()
    }

    @Test
    fun should_ShowProgressMessage_OnLoadingState() {
        setScreenContent()

        uiState.update { DetailUiState.Loading }

        composeTestRule.onNodeWithTag("DetailScreenLoading", useUnmergedTree = true).assertIsDisplayed()

        verify(exactly = 1) { viewModel.onUiEvent(DetailUiEvent.GetRepositoryData) }
    }

    @Test
    fun should_ShowProgressMessage_OnUninitializedState() {
        setScreenContent()

        composeTestRule.onNodeWithTag("DetailScreenLoading", useUnmergedTree = true).assertIsDisplayed()

        verify(exactly = 1) { viewModel.onUiEvent(DetailUiEvent.GetRepositoryData) }
    }

    @Test
    fun should_ShowErrorMessage_OnErrorState() {
        uiState.update { DetailUiState.Error }

        setScreenContent()

        composeTestRule.onNodeWithTag("DetailErrorMessage", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun should_TriggerBackEvent_whenBackButtonIsClicked_onPortrait() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult) }

        setScreenContent()

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        verify { viewModel.onUiEvent(DetailUiEvent.Back) }
    }

    @Test
    fun should_TriggerBackEvent_whenBackButtonIsClicked_onLandscape() {
        uiState.update { DetailUiState.Loaded(repository = expectedResult) }

        setScreenContent(Configuration.ORIENTATION_LANDSCAPE)

        composeTestRule.onNodeWithText("${expectedResult.name} by ${expectedResult.author}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ ${expectedResult.starCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 ${expectedResult.forkCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F ${expectedResult.watcherCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F ${expectedResult.issueCount}").assertIsDisplayed()
        composeTestRule.onNodeWithText(expectedResult.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: ${expectedResult.language}").assertIsDisplayed()
        composeTestRule.onNodeWithText("License: ${expectedResult.licenseName}").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        verify { viewModel.onUiEvent(DetailUiEvent.Back) }
    }

    private fun setScreenContent(
        orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    ) {
        composeTestRule.setContent {
            CompositionLocalProvider(
                LocalConfiguration provides Configuration().apply {
                    this.orientation = orientation
                }
            ) {
                DetailScreenStateOwner(
                    viewModel = viewModel,
                )
            }
        }
    }

    private companion object {
        val expectedResult = RepositoryVO(
            id = 1,
            name = "Test Repo",
            author = "Test Author",
            userAvatar = "http://example.com/avatar.png",
            description = "This is a test repository description.",
            starCount = 100,
            forkCount = 50,
            watcherCount = 20,
            issueCount = 5,
            language = "Kotlin",
            licenseName = "MIT",
            createdAt = Date(0),
            pushedAt = Date(0),
            updatedAt = Date(0),
        )
    }
}
