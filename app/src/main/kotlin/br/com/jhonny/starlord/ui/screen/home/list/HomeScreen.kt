package br.com.jhonny.starlord.ui.screen.home.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.jhonny.starlord.extension.Empty
import br.com.jhonny.starlord.ui.preview.DevicePreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender
import br.com.jhonny.starlord.ui.screen.home.component.ErrorMessage
import br.com.jhonny.starlord.ui.screen.home.component.GitRepositoryList
import br.com.jhonny.starlord.ui.screen.home.component.Header
import br.com.jhonny.starlord.ui.screen.home.component.ProgressMessage
import br.com.jhonny.starlord.ui.screen.home.component.SearchInput
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiEvent
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiState
import br.com.jhonny.starlord.ui.screen.home.provider.RepositoryPreviewProvider
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

public typealias OnHomeUiEvent = (HomeUiEvent) -> Unit
private const val SEARCH_DEBOUNCE_MILLIS = 500L

@Composable
public fun HomeScreeStateOwner(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        HomeUiState.Uninitialized,
        HomeUiState.Loading,
            -> {
            Column(
                modifier = Modifier
                    .testTag("HomeScreenLoading")
            ) {
                ProgressMessage(
                    modifier = modifier,
                )
            }

        }

        is HomeUiState.Loaded -> {
            HomeScreen(
                onUiEvent = viewModel::onUiEvent,
                repositories = state.repositories,
                modifier = modifier
                    .testTag("HomeScreen"),
            )
        }

        is HomeUiState.Error -> {
            ErrorMessage(
                onRetry = {
                    viewModel.onUiEvent(HomeUiEvent.RequestMoreData(state.searchTerm, state.languages))
                },
                modifier = modifier
                    .testTag("HomeScreenError"),
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onUiEvent(HomeUiEvent.RequestMoreData(String.Empty, emptyList()))
    }
}

@Composable
private fun HomeScreen(
    modifier: Modifier,
    repositories: List<RepositoryVO>,
    onUiEvent: OnHomeUiEvent,
) {
    HomeContent(
        modifier = modifier,
        repositories = repositories,
        onUiEvent = onUiEvent,
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    repositories: List<RepositoryVO>,
    onUiEvent: OnHomeUiEvent = {},
) {
    var searchTerm by rememberSaveable { mutableStateOf(String.Empty) }
    var selectedLanguages by rememberSaveable { mutableStateOf(emptyList<String>()) }
    var isStarting by remember { mutableStateOf(true) }

    LaunchedEffect(searchTerm, selectedLanguages) {
        if (isStarting) {
            isStarting = false
            return@LaunchedEffect
        }

        if (searchTerm.isNotEmpty() || selectedLanguages.isNotEmpty()) {
            delay(SEARCH_DEBOUNCE_MILLIS)
        }
        onUiEvent(HomeUiEvent.SearchRepositories(searchTerm, selectedLanguages))
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        Header(
            modifier = Modifier
                .testTag("HomeScreenHeader"),
        )
        SearchInput(
            searchTerm = searchTerm,
            selectedLanguages = selectedLanguages,
            onClearSelectedLanguages = { selectedLanguages = emptyList() },
            onLanguageToggled = {
                if (it !in selectedLanguages) {
                    selectedLanguages += it
                } else {
                    selectedLanguages -= it
                }
            },
            onSearchTermChanged = { searchTerm = it },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("HomeScreenSearchInput"),
        )

        GitRepositoryList(
            repositories = repositories,
            onLoadMore = { onUiEvent(HomeUiEvent.RequestMoreData(searchTerm, selectedLanguages)) },
        ) {
            onUiEvent(HomeUiEvent.ShowRepositoryInfo(it.id))
        }
    }
}

@DevicePreview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(RepositoryPreviewProvider::class)
    repositories: List<RepositoryVO>,
) {
    PreviewContentRender { modifier ->
        HomeContent(
            modifier = modifier,
            repositories = repositories,
        )
    }
}
