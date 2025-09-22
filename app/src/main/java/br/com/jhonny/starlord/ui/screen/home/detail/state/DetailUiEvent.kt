package br.com.jhonny.starlord.ui.screen.home.detail.state

public sealed interface DetailUiEvent {
    public data object Back : DetailUiEvent

    public data object GetRepositoryData : DetailUiEvent
}
