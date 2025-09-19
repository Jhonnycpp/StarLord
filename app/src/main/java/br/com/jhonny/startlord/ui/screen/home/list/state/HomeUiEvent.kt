package br.com.jhonny.startlord.ui.screen.home.list.state

public sealed interface HomeUiEvent {
    public data object RequestMoreData : HomeUiEvent
    public data class ShowRepositoryInfo(val id: Int) : HomeUiEvent
}
