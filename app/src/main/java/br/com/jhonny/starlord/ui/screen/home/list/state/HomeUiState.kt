package br.com.jhonny.starlord.ui.screen.home.list.state

import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

public sealed interface HomeUiState {
    public data object Uninitialized : HomeUiState
    public data object Loading : HomeUiState

    public data class Loaded(val repositories: List<RepositoryVO>) : HomeUiState

    public data object Error : HomeUiState
}
