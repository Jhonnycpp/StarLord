package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.extension.toImageUiState
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import java.util.Date

/**
 * A composable that displays a single Git repository item.
 * It shows essential information like the repository's name, author, star count,
 * fork count, and primary programming language, along with the author's avatar.
 * The item is clickable, allowing for navigation or other actions.
 *
 * @param modifier The modifier to be applied to the component.
 * @param item The [RepositoryVO] data to be displayed.
 * @param onClick A lambda function to be executed when the item is clicked.
 */
@Composable
internal fun GitRepositoryItem(
    modifier: Modifier = Modifier,
    item: RepositoryVO,
    onClick: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .testTag("GitRepositoryItem")
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.extraSmall,
            )
            .clickable(onClick = onClick),
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                context = LocalContext.current,
            ).data(data = item.userAvatar)
                .crossfade(true)
                .build(),
        )
        val state by painter.state.collectAsState()

        RepositoryImage(
            name = item.name,
            author = item.author,
            imageState = state.toImageUiState(),
            modifier = Modifier
                .testTag("RepositoryImage")
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Text(
            text = stringResource(R.string.repository_name_and_author, item.name, item.author),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 8.dp),
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
        ) {
            Text(
                text = "⭐ ${item.starCount}",
                style = MaterialTheme.typography.titleSmall,
            )

            Text(
                text = "🍴 ${item.forkCount}",
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
        ) {
            Text(
                text = "\uD83E\uDDD1\u200D\uD83D\uDCBB ${item.language}",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun GitRepositoryItemPreview() {
    PreviewContentRender { modifier ->
        GitRepositoryItem(
            modifier = modifier,
            item = RepositoryVO(
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
            ),
        )
    }
}
