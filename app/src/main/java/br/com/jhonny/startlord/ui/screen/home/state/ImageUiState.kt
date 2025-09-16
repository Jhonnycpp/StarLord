package br.com.jhonny.startlord.ui.screen.home.state

import androidx.compose.ui.graphics.painter.Painter

internal interface ImageUiState {
    data object Loading : ImageUiState
    data object Empty : ImageUiState
    data object Error : ImageUiState
    data class Success(val painter: Painter?) : ImageUiState
}
