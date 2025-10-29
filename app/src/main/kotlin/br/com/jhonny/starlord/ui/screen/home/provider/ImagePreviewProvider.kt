package br.com.jhonny.starlord.ui.screen.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.jhonny.starlord.ui.screen.home.list.state.ImageUiState
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import java.util.Date

/**
 * This class provides a sequence of `Pair<RepositoryVO, ImageUiState>` for use in Jetpack Compose previews.
 *
 * It is used to generate different states of the UI for previewing purposes. Each pair in the sequence represents a
 * specific combination of a `RepositoryVO` and an `ImageUiState`, allowing developers to visualize how the UI
 * behaves in various scenarios (e.g., loading, error, success, empty).
 *
 * The `values` property returns a sequence of these pairs.
 */
internal class ImagePreviewProvider : PreviewParameterProvider<Pair<RepositoryVO, ImageUiState>> {
    override val values: Sequence<Pair<RepositoryVO, ImageUiState>>
        get() = sequenceOf(
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                userAvatar = "https://picsum.photos/200?random=1",
                description = "The Magic Mask for Android",
                language = "Kotlin",
                licenseName = "GNU General Public License v3.0",
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date(),
                watcherCount = 33,
                issueCount = 2,
            ) to ImageUiState.Loading,
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                userAvatar = "https://picsum.photos/200?random=1",
                description = "The Magic Mask for Android",
                language = "Kotlin",
                licenseName = "GNU General Public License v3.0",
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date(),
                watcherCount = 33,
                issueCount = 2,
            ) to ImageUiState.Empty,
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                userAvatar = "https://picsum.photos/200?random=1",
                description = "The Magic Mask for Android",
                language = "Kotlin",
                licenseName = "GNU General Public License v3.0",
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date(),
                watcherCount = 33,
                issueCount = 2,
            ) to ImageUiState.Error,
            RepositoryVO(
                id = 1,
                name = "Lumos",
                author = "Jhonatan",
                starCount = 124,
                forkCount = 37,
                userAvatar = "https://picsum.photos/200?random=1",
                description = "The Magic Mask for Android",
                language = "Kotlin",
                licenseName = "GNU General Public License v3.0",
                createdAt = Date(),
                updatedAt = Date(),
                pushedAt = Date(),
                watcherCount = 33,
                issueCount = 2,
            ) to ImageUiState.Success(
                painter = null,
            ),
        )
}
