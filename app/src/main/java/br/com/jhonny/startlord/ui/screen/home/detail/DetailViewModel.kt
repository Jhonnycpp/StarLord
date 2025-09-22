package br.com.jhonny.startlord.ui.screen.home.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.jhonny.startlord.feature.home.usecase.RetrieveRepositoryUseCase
import br.com.jhonny.startlord.ui.navigation.Navigation
import br.com.jhonny.startlord.ui.navigation.Route
import br.com.jhonny.startlord.ui.screen.home.detail.state.DetailUiEvent
import br.com.jhonny.startlord.ui.screen.home.detail.state.DetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

public class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val navigation: Navigation,
    private val retrieveRepositoryUseCase: RetrieveRepositoryUseCase,
) : ViewModel() {
    private val repositoryDetail: Route.Detail? = runCatching {
        savedStateHandle.toRoute<Route.Detail>()
    }.getOrNull()

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Uninitialized)
    public val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    public fun onUiEvent(event: DetailUiEvent) {
        when (event) {
            DetailUiEvent.Back -> navigation.back()
            DetailUiEvent.GetRepositoryData -> {
                viewModelScope.launch {
                    val route = repositoryDetail ?: return@launch _uiState.update { DetailUiState.Error }

                    getRepositoryData(route)
                }
            }
        }
    }

    private suspend fun getRepositoryData(detail: Route.Detail) {
        val state = retrieveRepositoryUseCase(detail.id)?.let { DetailUiState.Loaded(it) } ?: DetailUiState.Error
        _uiState.update { state }
    }
}
