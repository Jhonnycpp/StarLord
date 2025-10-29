package br.com.jhonny.starlord.ui.screen.home.detail.state

/**
 * Represents the different UI events that can occur on the Detail screen.
 *
 * This sealed interface defines all possible user interactions or system events
 * that the Detail screen's ViewModel needs to handle.
 */
public sealed interface DetailUiEvent {
    /**
     * Event to navigate back from the detail screen.
     */
    public data object Back : DetailUiEvent

    /**
     * Event to trigger the fetching of repository data.
     * This event is used to signal the ViewModel to initiate a request to retrieve
     * details for a specific repository.
     */
    public data object GetRepositoryData : DetailUiEvent
}
