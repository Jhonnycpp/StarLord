package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProgressMessageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ProgressMessage()
        }
    }

    @Test
    fun should_ProgressMessage_IsDisplayed() {
        composeTestRule.onNodeWithTag("ProgressMessage").assertIsDisplayed()
    }
}
