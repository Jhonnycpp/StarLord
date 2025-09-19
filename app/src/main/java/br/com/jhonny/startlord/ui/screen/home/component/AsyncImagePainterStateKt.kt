package br.com.jhonny.startlord.ui.screen.home.component

import br.com.jhonny.startlord.ui.screen.home.list.state.ImageUiState
import coil3.compose.AsyncImagePainter

public fun AsyncImagePainter.State.toImageUiState(): ImageUiState = when (this) {
    is AsyncImagePainter.State.Empty -> ImageUiState.Empty
    is AsyncImagePainter.State.Error -> ImageUiState.Error
    is AsyncImagePainter.State.Loading -> ImageUiState.Loading
    is AsyncImagePainter.State.Success -> ImageUiState.Success(painter)
}
