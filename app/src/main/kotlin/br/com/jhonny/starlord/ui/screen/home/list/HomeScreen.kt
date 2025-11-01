package br.com.jhonny.starlord.ui.screen.home.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.jhonny.starlord.extension.Empty
import br.com.jhonny.starlord.ui.preview.DevicePreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender
import br.com.jhonny.starlord.ui.screen.home.component.ErrorMessage
import br.com.jhonny.starlord.ui.screen.home.component.GitRepositoryList
import br.com.jhonny.starlord.ui.screen.home.component.Header
import br.com.jhonny.starlord.ui.screen.home.component.LoadingOverlay
import br.com.jhonny.starlord.ui.screen.home.component.SearchInput
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiEvent
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiState
import br.com.jhonny.starlord.ui.screen.home.provider.RepositoryPreviewProvider
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
                LoadingOverlay(
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
    modifier: Modifier,
    repositories: List<RepositoryVO>,
    onUiEvent: OnHomeUiEvent,
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
    with(LocalConfiguration.current) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            HomePortrait(
                modifier = modifier
                    .testTag("HomeScreenPortrait"),
                searchTerm = searchTerm,
                selectedLanguages = selectedLanguages,
                repositories = repositories,
                onUiEvent = onUiEvent,
                onClearSelectedLanguages = { selectedLanguages = emptyList() },
                onLanguageToggled = {
                    if (it !in selectedLanguages) {
                        selectedLanguages += it
                    } else {
                        selectedLanguages -= it
                    }
                },
                onSearchTermChanged = { searchTerm = it },
                onLoadMore = { onUiEvent(HomeUiEvent.RequestMoreData(searchTerm, selectedLanguages)) },
                onItemClick = { onUiEvent(HomeUiEvent.ShowRepositoryInfo(it.id)) }
            )
        } else {
            HomeLandscape(
                modifier = modifier
                    .testTag("HomeScreenLandscape"),
                searchTerm = searchTerm,
                selectedLanguages = selectedLanguages,
                repositories = repositories,
                onUiEvent = onUiEvent,
                onClearSelectedLanguages = { selectedLanguages = emptyList() },
                onLanguageToggled = {
                    if (it !in selectedLanguages) {
                        selectedLanguages += it
                    } else {
                        selectedLanguages -= it
                    }
                },
                onSearchTermChanged = { searchTerm = it },
                onLoadMore = { onUiEvent(HomeUiEvent.RequestMoreData(searchTerm, selectedLanguages)) },
                onItemClick = { onUiEvent(HomeUiEvent.ShowRepositoryInfo(it.id)) }
            )
        }
    }
}

@Composable
private fun HomeLandscape(
    modifier: Modifier = Modifier,
    searchTerm: String,
    selectedLanguages: List<String>,
    repositories: List<RepositoryVO>,
    onUiEvent: OnHomeUiEvent,
    onSearchTermChanged: (String) -> Unit,
    onLanguageToggled: (String) -> Unit,
    onClearSelectedLanguages: () -> Unit,
    onLoadMore: () -> Unit,
    onItemClick: (RepositoryVO) -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    SearchInput(
                        searchTerm = searchTerm,
                        selectedLanguages = selectedLanguages,
                        onClearSelectedLanguages = onClearSelectedLanguages,
                        onLanguageToggled = onLanguageToggled,
                        onSearchTermChanged = onSearchTermChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .testTag("HomeScreenSearchInput"),
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .testTag("HomeScreenSearchButton"),
                        onClick = {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                        }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }

                    Header(
                        modifier = Modifier
                            .testTag("HomeScreenHeader")
                    )
                }

                GitRepositoryList(
                    repositories = repositories,
                    onLoadMore = onLoadMore,
                    onItemClick = onItemClick,
                )
            }
        }
    }
}

@Composable
private fun HomePortrait(
    modifier: Modifier = Modifier,
    searchTerm: String,
    selectedLanguages: List<String>,
    repositories: List<RepositoryVO>,
    onUiEvent: OnHomeUiEvent,
    onSearchTermChanged: (String) -> Unit,
    onLanguageToggled: (String) -> Unit,
    onClearSelectedLanguages: () -> Unit,
    onLoadMore: () -> Unit,
    onItemClick: (RepositoryVO) -> Unit,
) {

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
            onClearSelectedLanguages = onClearSelectedLanguages,
            onLanguageToggled = onLanguageToggled,
            onSearchTermChanged = onSearchTermChanged,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("HomeScreenSearchInput"),
        )

        GitRepositoryList(
            repositories = repositories,
            onLoadMore = onLoadMore,
            onItemClick = onItemClick,
        )
    }
}

@DevicePreview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(RepositoryPreviewProvider::class)
    repositories: List<RepositoryVO>,
) {
    PreviewContentRender { modifier ->
        HomeScreen(
            modifier = modifier,
            repositories = repositories,
            onUiEvent = {},
        )
    }
}
