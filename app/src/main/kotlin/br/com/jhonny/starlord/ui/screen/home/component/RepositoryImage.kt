package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender
import br.com.jhonny.starlord.ui.screen.home.list.state.ImageUiState
import br.com.jhonny.starlord.ui.screen.home.provider.ImagePreviewProvider
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO

/**
 * A Composable that displays an image for a repository based on the provided [ImageUiState].
 * It handles loading, error, and success states for the image.
 *
 * @param modifier The [Modifier] to be applied to this Composable.
 * @param name The name of the repository, used in the content description for accessibility.
 * @param author The author of the repository, used in the content description for accessibility.
 * @param imageState The current state of the image, which can be [ImageUiState.Loading],
 * [ImageUiState.Empty], [ImageUiState.Error], or [ImageUiState.Success]. The Composable will
 * render a different UI for each state.
 */
@Composable
internal fun RepositoryImage(
    modifier: Modifier = Modifier,
    name: String,
    author: String,
    imageState: ImageUiState,
) {
    when (imageState) {
        is ImageUiState.Loading,
        is ImageUiState.Empty,
            -> {
            Column(
                modifier = modifier
                    .testTag("ProgressIndicator")
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(32.dp)
                )
            }
        }

        is ImageUiState.Error -> {
            Image(
                painter = painterResource(id = R.drawable.ic_android_error),
                contentDescription = stringResource(R.string.error_image_content_description),
                modifier = modifier
                    .testTag("ErrorImage")
            )
        }

        is ImageUiState.Success -> {
            val (painter, contentDescription) = if (imageState.painter != null) {
                imageState.painter to stringResource(R.string.repository_image_content_provider, name, author)
            } else {
                painterResource(id = R.drawable.ic_launcher_foreground) to stringResource(R.string.error_image_content_description)
            }

            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .testTag("RepositoryImage"),
            )
        }
    }
}

@ComponentPreview
@Composable
private fun RepositoryImagePreview(
    @PreviewParameter(ImagePreviewProvider::class)
    data: Pair<RepositoryVO, ImageUiState>,
) {
    PreviewContentRender { modifier ->
        data.let { (repository, imageState) ->
            // This code is necessary because I can't instantiate a painter outside a Compose scope.
            val state = with(imageState) {
                if (this is ImageUiState.Success && painter == null) {
                    copy(painterResource(R.drawable.ic_launcher_foreground))
                } else {
                    this
                }
            }

            RepositoryImage(
                modifier = modifier,
                name = repository.name,
                author = repository.author,
                imageState = state,
            )
        }
    }
}
