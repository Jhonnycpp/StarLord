package br.com.jhonny.starlord.ui.screen.home.detail.state

import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

/**
 * Represents the different states of the detail screen UI.
 *
 * This interface defines the possible states that the detail screen can be in,
 * allowing for a clear and concise way to manage UI updates based on data loading
 * and error conditions.
 */
public interface DetailUiState {
    /**
     * Represents the uninitialized state of the detail screen.
     * This state is used before any data is loaded or any action is performed.
     */
    public data object Uninitialized : DetailUiState
    /**
     * Represents the loading state of the detail screen.
     * This state is active while data is being fetched.
     */
    public data object Loading : DetailUiState
    /**
     * Represents the state where the repository details have been successfully loaded.
     *
     * @property repository The [RepositoryVO] containing the details of the repository.
     */
    public data class Loaded(val repository: RepositoryVO) : DetailUiState
    /**
     * Represents the error state of the detail screen.
     */
    public data object Error : DetailUiState
}
