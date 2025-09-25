package br.com.jhonny.starlord.ui.screen.home.list.state

import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

/**
 * Represents the different states of the Home screen UI.
 * This sealed interface is used to manage the UI's appearance and behavior based on the current data loading status.
 *
 * - [Uninitialized]: The initial state before any data loading has begun.
 * - [Loading]: Indicates that data is currently being fetched or processed.
 * - [Loaded]: Represents the state where data has been successfully loaded and is available for display.
 * - [Error]: Indicates that an error occurred during data loading or processing.
 */
public sealed interface HomeUiState {
    /**
     * Initial state of the screen, before any data is loaded.
     */
    public data object Uninitialized : HomeUiState
    /**
     * Represents the loading state of the Home screen.
     * This state is used when the data is being fetched.
     */
    public data object Loading : HomeUiState

    /**
     * Represents the state where the list of repositories has been successfully loaded.
     *
     * @property repositories The list of repositories to be displayed.
     */
    public data class Loaded(val repositories: List<RepositoryVO>) : HomeUiState

    /**
     * Represents the error state of the home screen.
     * This state is used when an error occurs while fetching data.
     */
    public data object Error : HomeUiState
}
