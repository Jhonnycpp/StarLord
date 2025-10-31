package br.com.jhonny.starlord.ui.screen.home.list.state

/**
 * Represents the different UI events that can occur on the Home screen.
 * These events are typically triggered by user interactions and are used to communicate
 * actions from the UI layer to the ViewModel or other business logic components.
 */
public sealed interface HomeUiEvent {
    /**
     * Represents a user interface event indicating a request for more data.
     * This event is typically triggered when the user scrolls to the end of a list
     * or performs an action that requires loading additional items.
     */
    public data class RequestMoreData(
        val searchTerm: String,
        val languages: List<String>,
    ) : HomeUiEvent

    /**
     * Represents a user interface event to initiate a search for repositories.
     * This event is triggered when the user submits a search query, providing the
     * necessary parameters to perform the search.
     *
     * @property searchTerm The search term entered by the user.
     * @property languages A list of selected languages to filter the search results.
     */
    public data class SearchRepositories(
        val searchTerm: String,
        val languages: List<String>
    ) : HomeUiEvent

    /**
     * Event to indicate that the repository information for a specific ID should be displayed.
     *
     * @property id The unique identifier of the repository to display.
     */
    public data class ShowRepositoryInfo(val id: Int) : HomeUiEvent
}
