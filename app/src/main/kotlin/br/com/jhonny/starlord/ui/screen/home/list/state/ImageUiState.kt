package br.com.jhonny.starlord.ui.screen.home.list.state

import androidx.compose.ui.graphics.painter.Painter

/**
 * Represents the different states of an image loading operation in the UI.
 * This sealed interface is used to manage and display various UI elements
 * based on the current state of image fetching and rendering.
 */
public interface ImageUiState {
    /**
     * Represents the loading state of an image.
     */
    public data object Loading : ImageUiState
    /**
     * Represents the state where there is no image to display.
     */
    public data object Empty : ImageUiState
    /**
     * Represents the error state of an image.
     * This state is used when there is an error loading or displaying the image.
     */
    public data object Error : ImageUiState
    /**
     * Represents the state where the image has been successfully loaded.
     *
     * @property painter The [Painter] object representing the loaded image. Can be null if the image
     *                  was successfully loaded but is empty or invalid.
     */
    public data class Success(val painter: Painter?) : ImageUiState
}
