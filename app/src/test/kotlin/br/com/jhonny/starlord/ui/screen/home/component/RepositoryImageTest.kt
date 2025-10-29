package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.ui.screen.home.list.state.ImageUiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryImageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_Show_ProgressIndicator() {
        composeTestRule.setContent {
            RepositoryImage(
                name = "Test Repository",
                author = "Test Author",
                imageState = ImageUiState.Loading,
            )
        }

        composeTestRule.onNodeWithTag("ProgressIndicator").assertExists()
        composeTestRule.onNodeWithTag("ProgressIndicator").assertIsDisplayed()
    }

    @Test
    fun should_Show_ProgressIndicator_WithEmptyState() {
        composeTestRule.setContent {
            RepositoryImage(
                name = "Test Repository",
                author = "Test Author",
                imageState = ImageUiState.Empty,
            )
        }

        composeTestRule.onNodeWithTag("ProgressIndicator").assertExists()
        composeTestRule.onNodeWithTag("ProgressIndicator").assertIsDisplayed()
    }

    @Test
    fun should_Show_ErrorImage() {
        composeTestRule.setContent {
            RepositoryImage(
                name = "Test Repository",
                author = "Test Author",
                imageState = ImageUiState.Error,
            )
        }

        composeTestRule.onNodeWithTag("ErrorImage").assertExists()
        composeTestRule.onNodeWithTag("ErrorImage").assertIsDisplayed()
    }

    @Test
    fun should_Show_RepositoryImage_WithPainter() {
        composeTestRule.setContent {
            RepositoryImage(
                name = "Test Repository",
                author = "Test Author",
                imageState = ImageUiState.Success(painterResource(R.drawable.ic_launcher_foreground)),
            )
        }

        composeTestRule.onNodeWithTag("RepositoryImage").assertExists()
        composeTestRule.onNodeWithTag("RepositoryImage").assertIsDisplayed()
    }

    @Test
    fun should_Show_RepositoryImage_WithoutPainter() {
        composeTestRule.setContent {
            RepositoryImage(
                name = "Test Repository",
                author = "Test Author",
                imageState = ImageUiState.Success(null),
            )
        }

        composeTestRule.onNodeWithTag("RepositoryImage").assertExists()
        composeTestRule.onNodeWithTag("RepositoryImage").assertIsDisplayed()
    }
}
