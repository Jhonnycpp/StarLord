package br.com.jhonny.startlord.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.jhonny.startlord.R
import br.com.jhonny.startlord.ui.ComponentPreview
import br.com.jhonny.startlord.ui.screen.home.provider.ImagePreviewProvider
import br.com.jhonny.startlord.ui.screen.home.state.ImageUiState
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO
import br.com.jhonny.startlord.ui.theme.StartLordTheme

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
            Column(modifier) {
                CircularProgressIndicator(
                    modifier = Modifier.Companion.padding(32.dp)
                )
            }
        }

        is ImageUiState.Error -> {
            Image(
                painter = painterResource(id = R.drawable.ic_android_error),
                contentDescription = "Error loading image"
            )
        }

        is ImageUiState.Success -> {
            val (painter, contentDescription) = if (imageState.painter != null) {
                imageState.painter to "This is an image represents the repository $name and created by $author"
            }
            else {
                painterResource(id = R.drawable.ic_launcher_foreground) to ""
            }

            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@ComponentPreview
@Composable
private fun RepositoryImagePreview(
    @PreviewParameter(ImagePreviewProvider ::class)
    data: Pair<RepositoryVO, ImageUiState>,
) {
    StartLordTheme {
        Scaffold { innerPadding ->
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
                    modifier = Modifier.padding(
                        paddingValues = innerPadding
                    ),
                    name = repository.name,
                    author = repository.author,
                    imageState = state,
                )
            }
        }
    }
}
