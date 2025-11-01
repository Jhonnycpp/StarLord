package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val onSearchTermChanged: (String) -> Unit = mockk(relaxed = true)
    private val onLanguageToggled: (String) -> Unit = mockk(relaxed = true)
    private val onClearSelectedLanguages: () -> Unit = mockk(relaxed = true)

    @Test
    fun `should display initial state correctly`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "",
                selectedLanguages = emptyList(),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages
            )
        }

        composeTestRule.onNodeWithTag("SearchInput").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LanguageSelectionList").assertIsDisplayed()
        composeTestRule.onNodeWithTag("RepositorySearchField").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SelectedLanguagesHeader").assertDoesNotExist()
        composeTestRule.onNodeWithTag("ClearSearchFieldButton").assertDoesNotExist()
    }

    @Test
    fun `should call onLanguageToggled when a language is clicked`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "",
                selectedLanguages = emptyList(),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages,
                acceptedLanguages = listOf("kotlin", "java")
            )
        }

        composeTestRule.onNodeWithTag("LanguageItem_kotlin").performClick()

        verify { onLanguageToggled("kotlin") }
    }

    @Test
    fun `should display selected languages and header`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "",
                selectedLanguages = listOf("kotlin", "java"),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages,
                acceptedLanguages = listOf("kotlin", "java")
            )
        }

        composeTestRule.onNodeWithTag("SelectedLanguagesHeader").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LanguageBadge_kotlin").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LanguageBadge_java").assertIsDisplayed()
        composeTestRule.onNodeWithTag("LanguageSelectedIcon_kotlin", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("LanguageSelectedIcon_java", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun `should call onLanguageToggled when removing a language`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "",
                selectedLanguages = listOf("kotlin"),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages
            )
        }

        composeTestRule.onNodeWithTag("RemoveLanguage_kotlin").performClick()

        verify { onLanguageToggled("kotlin") }
    }

    @Test
    fun `should call onClearSelectedLanguages when clear button is clicked`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "",
                selectedLanguages = listOf("kotlin", "java"),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages
            )
        }

        composeTestRule.onNodeWithTag("ClearSelectedLanguagesButton").performClick()

        verify { onClearSelectedLanguages() }
    }

    @Test
    fun `should call onSearchTermChanged when typing in search field`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "",
                selectedLanguages = emptyList(),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages
            )
        }

        composeTestRule.onNodeWithTag("RepositorySearchField").performTextInput("new query")

        verify { onSearchTermChanged("new query") }
    }

    @Test
    fun `should call onSearchTermChanged with empty string when clearing search field`() {
        composeTestRule.setContent {
            SearchInput(
                searchTerm = "some text",
                selectedLanguages = emptyList(),
                onSearchTermChanged = onSearchTermChanged,
                onLanguageToggled = onLanguageToggled,
                onClearSelectedLanguages = onClearSelectedLanguages
            )
        }

        composeTestRule.onNodeWithTag("ClearSearchFieldButton").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ClearSearchFieldButton").performClick()

        verify { onSearchTermChanged("") }
    }
}
