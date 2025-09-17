package br.com.jhonny.startlord.ui.screen.home

import android.util.Log
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
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.ExperimentalAtomicApi

@OptIn(ExperimentalAtomicApi::class)
public class HomeViewModel(
    private val retrieveGitHubRepositoryUseCase: RetrieveGitHubRepositoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Uninitialized)
    public val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val canLoadMore = AtomicBoolean(true)

    public fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.RequestMoreData -> requestMoreData()
            HomeUiEvent.ShowRepositoryInfo -> {}
        }
    }

    private fun requestMoreData() {
        Log.d("HomeViewModel", "Starting to requesting more data.")
        if (!canLoadMore.compareAndSet(expectedValue = true, newValue = false)) {
            Log.d("HomeViewModel", "Can't load more data.")

            return
        }

        viewModelScope.launch {
            Log.d("HomeViewModel", "Requesting more data.")
            val repositories = retrieveGitHubRepositoryUseCase()
            _uiState.update { repositories.toHomeUiState() }
            canLoadMore.store(true)
            Log.d("HomeViewModel", "Finished to requesting more data.")
        }
    }

    private fun List<RepositoryVO>.toHomeUiState() = if (isNotEmpty()) {
        HomeUiState.Loaded(this)
    } else {
        HomeUiState.Error
    }
}
