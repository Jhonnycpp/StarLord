package br.com.jhonny.startlord.ui.screen.home.state

import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO

public sealed interface HomeUiState {
    public data object Uninitialized : HomeUiState
    public data object Loading : HomeUiState

    public data class Loaded(val repositories: List<RepositoryVO>) : HomeUiState

    public data object Error : HomeUiState
}
