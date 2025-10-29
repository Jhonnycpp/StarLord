package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class GitRepositoryItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GitRepositoryItem(
                item = RepositoryVO(
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
                    forkCount = 2,
                ),
            )
        }
    }

    @Test
    fun should_Successfully_ShowRepository_Info() {
        composeTestRule.onNodeWithTag("GitRepositoryItem").assertExists()
        composeTestRule.onNodeWithTag("GitRepositoryItem").assertIsDisplayed()

        composeTestRule.onNodeWithTag("GitRepositoryItem").assertTextContains("name by author")
        composeTestRule.onNodeWithTag("GitRepositoryItem").assertTextContains("⭐ 1")
        composeTestRule.onNodeWithTag("GitRepositoryItem").assertTextContains("🍴 2")


        composeTestRule.onNodeWithTag("RepositoryImage", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("RepositoryImage", useUnmergedTree = true).assertIsDisplayed()
    }
}
