package br.com.jhonny.startlord.ui.screen.home.state

public sealed interface HomeUiEvent {
    public data object RequestMoreData : HomeUiEvent
    public data object ShowRepositoryInfo : HomeUiEvent
}
