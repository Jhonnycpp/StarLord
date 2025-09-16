package br.com.jhonny.startlord.ui.screen.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.jhonny.startlord.ui.screen.home.state.ImageUiState
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO

internal class ImagePreviewProvider : PreviewParameterProvider<Pair<RepositoryVO, ImageUiState>> {
    override val values: Sequence<Pair<RepositoryVO, ImageUiState>>
        get() = sequenceOf(
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                imageUrl = "https://picsum.photos/200?random=1"
            ) to ImageUiState.Loading,
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                imageUrl = "https://picsum.photos/200?random=1"
            ) to ImageUiState.Empty,
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                imageUrl = "https://picsum.photos/200?random=1"
            ) to ImageUiState.Error,
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                imageUrl = "https://picsum.photos/200?random=1"
            ) to ImageUiState.Success(
                painter = null,
            ),
        )
}
