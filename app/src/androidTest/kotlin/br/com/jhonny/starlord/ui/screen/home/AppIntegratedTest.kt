package br.com.jhonny.starlord.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.LargeTest
import br.com.jhonny.starlord.di.mainModule
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryDTO
import br.com.jhonny.starlord.feature.home.dto.GitHubRepositoryResponse
import br.com.jhonny.starlord.feature.home.dto.LicenseDTO
import br.com.jhonny.starlord.feature.home.dto.RepositoryOwnerDTO
import br.com.jhonny.starlord.feature.home.service.GitHubRepositoryService
import br.com.jhonny.starlord.ui.AppContent
import br.com.jhonny.starlord.ui.navigation.createNavGraph
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import retrofit2.Response

@LargeTest
class AppIntegratedTest {
    private val gitHubRepository = mockk<GitHubRepositoryService>()

    @get:Rule(order = 0)
    val koin: KoinTestRule = KoinTestRule.create {
        modules(
            mainModule,
            module {
                single { gitHubRepository }
            }
        )
    }

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenAppStarts_shouldDisplayLoadingIndicator() {
        coEvery {
            gitHubRepository.getRepositories(any(), any(), any(), any(), any())
        } coAnswers {
            suspendCancellableCoroutine { }
        }

        setContent()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule
                .onAllNodesWithTag("HomeScreenLoading", useUnmergedTree = true)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule.onNodeWithTag("HomeScreenLoading", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun whenAppStarts_shouldDisplayListOfRepositories_and_go_to_detail() {
        coEvery {
            gitHubRepository.getRepositories(
                query = any(),
                sort = any(),
                page = any(),
                order = any(),
                perPage = any(),
            )
        } returnsMany listOf(successResponse, Response.success(null), successResponse, Response.success(null))

        setContent()

        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList", useUnmergedTree = true).assertIsDisplayed()

        composeTestRule.onRoot().printToLog("HomeScreen")
        composeTestRule.onNodeWithTag("RepositoryNameAndAuthor", useUnmergedTree = true)
            .assertTextContains("name by author")

        composeTestRule.onNodeWithTag("RepositoryStarCount", useUnmergedTree = true)
            .assertTextContains("⭐ 1")

        composeTestRule.onNodeWithTag("RepositoryForkCount", useUnmergedTree = true)
            .assertTextContains("🍴 1")

        composeTestRule.onNodeWithTag("RepositoryLanguage", useUnmergedTree = true)
            .assertTextContains("\uD83E\uDDD1\u200D\uD83D\uDCBB language")

        val itemsTree = composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true)
        val firstItem = itemsTree[0]

        firstItem.assertIsDisplayed()
        firstItem.performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onRoot().printToLog("DetailScreen")
        composeTestRule.onNodeWithText("name by author").assertIsDisplayed()
        composeTestRule.onNodeWithText("⭐ 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("🍴 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("\uD83D\uDC41\uFE0F 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("⚠\uFE0F 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("description").assertIsDisplayed()
        composeTestRule.onNodeWithText("License: Apache License 2.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("Language: language").assertIsDisplayed()

        val activity = composeTestRule.activity

        activity.onBackPressedDispatcher.onBackPressed()
        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("HomeScreen", useUnmergedTree = true).assertIsDisplayed()

        composeTestRule.onNodeWithTag("GitRepositoryList").performScrollToIndex(0)

        composeTestRule.waitForIdle()

        composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true).assertCountEquals(2)
    }

    @Test
    fun whenApiReturnsError_shouldDisplayErrorMessage() {
        coEvery {
            gitHubRepository.getRepositories(any(), any(), any(), any(), any())
        } returns errorResponse

        setContent()

        composeTestRule.onNodeWithTag("HomeScreenError", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    private fun setContent() {
        composeTestRule.setContent {
            val navController: NavHostController = rememberNavController()
            val navGraph = remember { navController.createNavGraph() }

            AppContent(
                navController = navController,
                navGraph = navGraph,
            )
        }
    }

    private companion object {
        val successResponse: Response<GitHubRepositoryResponse> = Response.success(
            GitHubRepositoryResponse(
                items = listOf(
                    GitHubRepositoryDTO(
                        id = 1,
                        name = "name",
                        description = "description",
                        owner = RepositoryOwnerDTO(
                            author = "author",
                            avatar = "avatar",
                        ),
                        language = "language",
                        license = LicenseDTO("Apache License 2.0"),
                        createdAt = "1970-01-01T00:00:00.00Z",
                        updatedAt = "1970-01-01T00:00:00.00Z",
                        pushedAt = "1970-01-01T00:00:00.00Z",
                        forksCount = 1,
                        watchersCount = 1,
                        openIssuesCount = 1,
                        starCount = 1,
                    )
                ),
                totalCount = 1,
            )
        )

        val errorResponse: Response<GitHubRepositoryResponse> = Response.error(
            404,
            "{\"message\":\"Not Found\"}".toResponseBody(null)
        )
    }
}
