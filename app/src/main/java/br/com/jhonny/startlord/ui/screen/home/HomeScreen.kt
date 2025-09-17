package br.com.jhonny.startlord.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.jhonny.startlord.ui.DevicePreview
import br.com.jhonny.startlord.ui.screen.home.component.ErrorMessage
import br.com.jhonny.startlord.ui.screen.home.component.GitRepositoryList
import br.com.jhonny.startlord.ui.screen.home.component.ProgressMessage
import br.com.jhonny.startlord.ui.screen.home.provider.RepositoryPreviewProvider
import br.com.jhonny.startlord.ui.screen.home.state.HomeUiEvent
import br.com.jhonny.startlord.ui.screen.home.state.HomeUiState
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO
import br.com.jhonny.startlord.ui.theme.StartLordTheme
import org.koin.androidx.compose.koinViewModel

public typealias OnHomeUiEvent = (HomeUiEvent) -> Unit

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
            Log.d("HomeScreen", "$state")
            ProgressMessage(
                modifier = modifier,
            )

            LaunchedEffect(Unit) {
                viewModel.onUiEvent(HomeUiEvent.RequestMoreData)
            }
        }

        is HomeUiState.Loaded -> {
            Log.d("HomeScreen", "HomeUiState.Loaded")
            HomeScreen(
                onUiEvent = viewModel::onUiEvent,
                repositories = state.repositories,
                modifier = modifier,
            )
        }

        is HomeUiState.Error -> {
            Log.d("HomeScreen", "HomeUiState.Error")
            ErrorMessage(
                onUiEvent = viewModel::onUiEvent,
                modifier = modifier,
            )
        }
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
    onUiEvent: OnHomeUiEvent,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        Header()
        GitRepositoryList(repositories = repositories)
    }
}

@DevicePreview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(RepositoryPreviewProvider::class)
    repositories: List<RepositoryVO>,
) {
    StartLordTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
                repositories = repositories,
            ) {

            }
        }
    }
}
