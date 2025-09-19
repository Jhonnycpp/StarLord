package br.com.jhonny.startlord.ui.screen.home.detail.state

import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO

public interface DetailUiState {
    public data object Uninitialized : DetailUiState
    public data object Loading : DetailUiState
    public data class Loaded(val repository: RepositoryVO) : DetailUiState
    public data object Error : DetailUiState
}
