package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class ErrorMessageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_ErrorMessage_IsDisplayed() {
        composeTestRule.setContent {
            ErrorMessage()
        }

        composeTestRule.onNodeWithTag("ErrorMessage").assertExists()
        composeTestRule.onNodeWithTag("ErrorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("RetryButton").assertExists()
        composeTestRule.onNodeWithTag("RetryButton").assertIsDisplayed()
    }

    @Test
    fun should_ErrorMessage_TriggerRetryEvent_RetryButtonClick() {
        var retryEventTriggered = false

        composeTestRule.setContent {
            ErrorMessage { retryEventTriggered = true }
        }

        composeTestRule.onNodeWithTag("RetryButton").assertExists()
        composeTestRule.onNodeWithTag("RetryButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("RetryButton").performClick()
        assertTrue(retryEventTriggered)
    }
}
