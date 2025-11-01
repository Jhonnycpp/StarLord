package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.extension.Empty
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender

/**
 * A composable that provides a user interface for searching repositories.
 * It includes a text field for a search term and a way to filter by programming languages.
 *
 * This component is composed of several parts:
 * 1.  A horizontal list of selectable programming languages (`LanguageSelectionList`).
 * 2.  A header and a list of badges for currently selected languages, which appear only when at least one language is selected (`SelectedLanguagesHeader` and `SelectedLanguagesBadges`).
 * 3.  A text input field for the user to type their search query (`RepositorySearchField`).
 *
 * @param modifier The modifier to be applied to the component's root Column.
 * @param searchTerm The current text in the search input field.
 * @param selectedLanguages A list of strings representing the currently selected programming languages for filtering.
 * @param onSearchTermChanged A callback function invoked when the user types in the search field. It receives the new search term as a `String`.
 * @param onLanguageToggled A callback function invoked when a user clicks on a language to select or deselect it. It receives the language name as a `String`.
 * @param onClearSelectedLanguages A callback function invoked when the user clicks the "clear all" button for selected languages.
 * @param acceptedLanguages A list of all available programming languages that can be selected for filtering. Defaults to a predefined list.
 */
@Composable
public fun SearchInput(
    modifier: Modifier = Modifier,
    searchTerm: String,
    selectedLanguages: List<String>,
    onSearchTermChanged: (String) -> Unit,
    onLanguageToggled: (String) -> Unit,
    onClearSelectedLanguages: () -> Unit,
    acceptedLanguages: List<String> = listOf("kotlin", "java", "javascript", "c++", "c", "c#", "python", "scala", "delphi", "ruby", "pearl", "react", "angular", "shell"),
) {
    Column(
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .testTag("SearchInput"),
    ) {
        Text(
            text = stringResource(R.string.search_input_language_header),
            style = MaterialTheme.typography.titleMedium,
        )

        LanguageSelectionList(
            languages = acceptedLanguages,
            selectedLanguages = selectedLanguages,
            onLanguageToggled = onLanguageToggled,
        )

        AnimatedVisibility(
            visible = selectedLanguages.isNotEmpty(),
        ) {
            SelectedLanguagesHeader(
                onClearClick = onClearSelectedLanguages,
            )
        }

        SelectedLanguagesBadges(
            selectedLanguages = selectedLanguages,
            onLanguageRemoved = onLanguageToggled,
        )

        RepositorySearchField(
            value = searchTerm,
            onValueChange = onSearchTermChanged,
            onClearClick = { onSearchTermChanged(String.Empty) }
        )
    }
}

@Composable
private fun LanguageSelectionList(
    modifier: Modifier = Modifier,
    languages: List<String>,
    selectedLanguages: List<String>,
    onLanguageToggled: (String) -> Unit,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("LanguageSelectionList"),
    ) {
        items(languages) { language ->
            Card(
                Modifier
                    .padding(all = 8.dp)
                    .clickable { onLanguageToggled(language) }
                    .testTag("LanguageItem_$language"),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 78.dp)
                        .padding(all = 8.dp),
                ) {
                    AnimatedVisibility(
                        visible = language in selectedLanguages,
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp)
                                .padding(end = 4.dp)
                                .testTag("LanguageSelectedIcon_$language"),
                        )
                    }
                    Text(
                        text = language,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectedLanguagesHeader(
    modifier: Modifier = Modifier,
    onClearClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .testTag("SelectedLanguagesHeader"),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.search_input_header_selected_languages),
            style = MaterialTheme.typography.titleMedium,
        )

        IconButton(
            onClick = onClearClick,
            modifier = Modifier.testTag("ClearSelectedLanguagesButton")
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = stringResource(R.string.search_input_clear_all_selected_languages),
            )
        }
    }
}

@Composable
private fun SelectedLanguagesBadges(
    modifier: Modifier = Modifier,
    selectedLanguages: List<String>,
    onLanguageRemoved: (String) -> Unit,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .testTag("SelectedLanguagesBadges"),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        selectedLanguages.forEach { language ->
            Badge(
                modifier = Modifier
                    .padding(4.dp)
                    .testTag("LanguageBadge_$language")
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = language,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(4.dp),
                        softWrap = false
                    )

                    Icon(
                        Icons.Default.Clear,
                        contentDescription = stringResource(R.string.search_input_clear_selected_language, language),
                        modifier = Modifier
                            .clickable { onLanguageRemoved(language) }
                            .testTag("RemoveLanguage_$language")
                    )
                }
            }
        }
    }
}

@Composable
private fun RepositorySearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Row {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(stringResource(R.string.search_input_field_header))
            }
        },
        placeholder = {
            Text(stringResource(R.string.search_input_placeholder))
        },
        trailingIcon = {
            AnimatedVisibility(visible = value.isNotEmpty()) {
                IconButton(
                    onClick = onClearClick,
                    modifier = Modifier.testTag("ClearSearchFieldButton")
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = stringResource(R.string.search_input_clear_field),
                    )
                }
            }
        },
        singleLine = true,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .testTag("RepositorySearchField"),
    )
}


@ComponentPreview
@Composable
private fun SearchInputPreview() {
    PreviewContentRender { modifier ->
        SearchInput(
            modifier = modifier,
            searchTerm = String.Empty,
            selectedLanguages = listOf("kotlin", "java"),
            onSearchTermChanged = {},
            onLanguageToggled = {},
            onClearSelectedLanguages = {},
        )
    }
}
