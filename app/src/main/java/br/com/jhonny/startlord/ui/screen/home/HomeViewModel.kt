package br.com.jhonny.startlord.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jhonny.startlord.feature.home.RetrieveGitHubRepositoryUseCase
import br.com.jhonny.startlord.ui.screen.home.state.HomeUiEvent
import br.com.jhonny.startlord.ui.screen.home.state.HomeUiState
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

public class HomeViewModel(
    private val retrieveGitHubRepositoryUseCase: RetrieveGitHubRepositoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Uninitialized)
    public val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    public fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.RequestMoreData -> {
                viewModelScope.launch {
                    val repositories = retrieveGitHubRepositoryUseCase(1)
                    _uiState.update { repositories.toHomeUiState() }
                }
            }

            HomeUiEvent.ShowRepositoryInfo -> {

            }
        }
    }

    private fun List<RepositoryVO>.toHomeUiState() = if (isNotEmpty()) {
        HomeUiState.Loaded(this)
    } else {
        HomeUiState.Error
    }
}
