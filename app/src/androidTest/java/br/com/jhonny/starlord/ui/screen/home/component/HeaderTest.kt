package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeaderTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Header()
        }
    }

    @Test
    fun should_Header_Is_Displayed() {
        composeTestRule.onNodeWithTag("Header").assertIsDisplayed()
    }
}
