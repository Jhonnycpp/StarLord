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
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.extension.Empty
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender

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
            .fillMaxWidth(),
    ) {
        Text(
            text = "Repositories with what languages?",
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
            .padding(vertical = 8.dp),
    ) {
        items(languages) { language ->
            Card(
                Modifier
                    .padding(all = 8.dp)
                    .clickable { onLanguageToggled(language) },
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
                                .padding(end = 4.dp),
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
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Selected languages",
            style = MaterialTheme.typography.titleMedium,
        )

        IconButton(onClick = onClearClick) {
            Icon(
                Icons.Default.Clear,
                contentDescription = "Clear all selected languages",
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        selectedLanguages.forEach { language ->
            Badge(
                modifier = Modifier.padding(4.dp)
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
                        contentDescription = "Remove $language",
                        modifier = Modifier.clickable { onLanguageRemoved(language) }
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
                Text("Find a repository")
            }
        },
        placeholder = {
            Text("Query data by name, readme or description.")
        },
        trailingIcon = {
            AnimatedVisibility(visible = value.isNotEmpty()) {
                IconButton(onClick = onClearClick) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Clear search term",
                    )
                }
            }
        },
        singleLine = true,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
    )
}


@ComponentPreview
@Composable
private fun SearchInputPreview() {
    PreviewContentRender { modifier ->
        SearchInput(
            modifier = modifier,
            searchTerm = "kotlin",
            selectedLanguages = listOf("kotlin", "java"),
            onSearchTermChanged = {},
            onLanguageToggled = {},
            onClearSelectedLanguages = {},
        )
    }
}
