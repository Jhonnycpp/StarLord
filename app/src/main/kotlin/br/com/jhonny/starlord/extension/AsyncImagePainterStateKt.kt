package br.com.jhonny.starlord.extension

import br.com.jhonny.starlord.ui.screen.home.list.state.ImageUiState
import coil3.compose.AsyncImagePainter

/**
 * Converts an [AsyncImagePainter.State] to an [ImageUiState].
 *
 * This function maps the different states of an [AsyncImagePainter.State] to the corresponding
 * [ImageUiState] values.
 *
 * @return The [ImageUiState] representation of the [AsyncImagePainter.State].
 */
public fun AsyncImagePainter.State.toImageUiState(): ImageUiState = when (this) {
    is AsyncImagePainter.State.Empty -> ImageUiState.Empty
    is AsyncImagePainter.State.Error -> ImageUiState.Error
    is AsyncImagePainter.State.Loading -> ImageUiState.Loading
    is AsyncImagePainter.State.Success -> ImageUiState.Success(painter)
}
