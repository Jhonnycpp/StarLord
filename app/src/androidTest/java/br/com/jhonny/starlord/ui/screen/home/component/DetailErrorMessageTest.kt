package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiEvent
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailErrorMessageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_DetailErrorMessage_IsDisplayed() {
        composeTestRule.setContent {
            DetailErrorMessage()
        }

        composeTestRule.onNodeWithTag("DetailErrorMessage").assertExists()
        composeTestRule.onNodeWithTag("DetailErrorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("RetryButton").assertExists()
        composeTestRule.onNodeWithTag("RetryButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BackButton").assertExists()
        composeTestRule.onNodeWithTag("BackButton").assertIsDisplayed()
    }

    @Test
    fun should_DetailErrorMessage_TriggerRetryEvent_RetryButtonClick() {
        composeTestRule.setContent {
            DetailErrorMessage { event ->
                assertEquals(DetailUiEvent.GetRepositoryData, event)
            }
        }

        composeTestRule.onNodeWithTag("RetryButton").assertExists()
        composeTestRule.onNodeWithTag("RetryButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("RetryButton").performClick()
    }

    @Test
    fun should_DetailErrorMessage_TriggerBackEvent_BackButtonClick() {
        composeTestRule.setContent {
            DetailErrorMessage { event ->
                assertEquals(DetailUiEvent.Back, event)
            }
        }

        composeTestRule.onNodeWithTag("BackButton").assertExists()
        composeTestRule.onNodeWithTag("BackButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("BackButton").performClick()
    }
}
