package br.com.jhonny.startlord.ui.screen.home.list.state

import androidx.compose.ui.graphics.painter.Painter

public interface ImageUiState {
    public data object Loading : ImageUiState
    public data object Empty : ImageUiState
    public data object Error : ImageUiState
    public data class Success(val painter: Painter?) : ImageUiState
}
