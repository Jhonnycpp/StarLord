package br.com.jhonny.starlord.ui.screen.home.list.state

public sealed interface HomeUiEvent {
    public data object RequestMoreData : HomeUiEvent
    public data class ShowRepositoryInfo(val id: Int) : HomeUiEvent
}
