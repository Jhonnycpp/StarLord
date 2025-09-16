package br.com.jhonny.startlord.ui.screen.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.jhonny.startlord.ui.ComponentPreview
import br.com.jhonny.startlord.ui.screen.home.state.ImageUiState
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO
import br.com.jhonny.startlord.ui.theme.StartLordTheme
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
internal fun GitRepositoryItem(
    modifier: Modifier = Modifier,
    item: RepositoryVO,
) {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.extraSmall,
            ),
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                context = LocalContext.current,
            )
                .data(data = item.imageUrl)
                .crossfade(true)
                .build()
        )
        val state by painter.state.collectAsState()

        Text(
            text = "Item ${item.id}",
            modifier = Modifier.Companion
                .padding(8.dp),
        )

        RepositoryImage(
            name = item.name,
            author = item.author,
            imageState = state.toImageUiState(),
        )

        Text(
            text = "${item.name} by ${item.author}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.Companion
                .padding(start = 8.dp),
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.Companion
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
    }
}

private fun AsyncImagePainter.State.toImageUiState() = when (this) {
    is AsyncImagePainter.State.Empty -> ImageUiState.Empty
    is AsyncImagePainter.State.Error -> ImageUiState.Error
    is AsyncImagePainter.State.Loading -> ImageUiState.Loading
    is AsyncImagePainter.State.Success -> ImageUiState.Success(painter)
}

@ComponentPreview
@Composable
private fun GitRepositoryItemPreview() {
    StartLordTheme {
        Scaffold { innerPadding ->
            GitRepositoryItem(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
                item = RepositoryVO(
                    id = 1,
                    name = "Lumos",
                    author = "Jhonatan",
                    starCount = 124,
                    forkCount = 37,
                    imageUrl = "https://picsum.photos/200?random=1"
                ),
            )
        }
    }
}
