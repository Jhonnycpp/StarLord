package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class GitRepositoryListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_Successfully_ShowRepositoryList_With3Elements() {
        composeTestRule.setContent {
            GitRepositoryList(
                repositories = buildRepositories(3)
            )
        }

        composeTestRule.onNodeWithTag("GitRepositoryList").assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList").assertIsDisplayed()

        composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true).assertCountEquals(3)
    }

    @Test
    fun should_Successfully_ShowRepositoryList_And_PerformClick_ToSeeDetails() {
        val expectedRepositories = buildRepositories(3)
        composeTestRule.setContent {
            GitRepositoryList(
                repositories = buildRepositories(3),
                onItemClick = {
                    assertEquals(expectedRepositories.first(), it)
                }
            )
        }

        composeTestRule.onNodeWithTag("GitRepositoryList").assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList").assertIsDisplayed()

        val itemsTree = composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true)
        val firstItem = itemsTree[0]

        firstItem.assertIsDisplayed()
        firstItem.performClick()
    }

    @Test
    fun should_Successfully_ShowRepositoryList_With4Elements() {
        composeTestRule.setContent {
            GitRepositoryList(
                repositories = buildRepositories(4)
            )
        }

        composeTestRule.onNodeWithTag("GitRepositoryList").assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryList").assertIsDisplayed()

        composeTestRule.onAllNodesWithTag("GitRepositoryItem", useUnmergedTree = true).assertCountEquals(4)
    }

    @Test
    fun should_Successfully_ShowRepositoryList_With20Elements_And_LoadMoreWasCalled() {
        var loadMoreIsCalled = false
        val repositories = buildRepositories(20)

        composeTestRule.setContent {
            GitRepositoryList(
                repositories = repositories,
                onLoadMore = { loadMoreIsCalled = true }
            )
        }

        composeTestRule.onNodeWithTag("GitRepositoryList").performScrollToIndex(repositories.size - 1)

        composeTestRule.waitForIdle()

        assertTrue(loadMoreIsCalled)
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
