package br.com.jhonny.startlord.ui.screen.home

import androidx.lifecycle.ViewModel
import br.com.jhonny.startlord.ui.screen.home.provider.RepositoryPreviewProvider
import br.com.jhonny.startlord.ui.screen.home.state.HomeUiEvent
import br.com.jhonny.startlord.ui.screen.home.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

public class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Uninitialized)
    public val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    public fun onUiEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.RequestMoreData -> {
                _uiState.update {
                    HomeUiState.Loaded(RepositoryPreviewProvider.sample)
                }
            }

            HomeUiEvent.ShowRepositoryInfo -> {

            }
        }
    }
}
