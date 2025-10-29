package br.com.jhonny.starlord.ui.screen.home.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.extension.toImageUiState
import br.com.jhonny.starlord.ui.DevicePreview
import br.com.jhonny.starlord.ui.screen.home.component.DetailErrorMessage
import br.com.jhonny.starlord.ui.screen.home.component.Header
import br.com.jhonny.starlord.ui.screen.home.component.ProgressMessage
import br.com.jhonny.starlord.ui.screen.home.component.RepositoryImage
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiEvent
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiState
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import br.com.jhonny.starlord.ui.theme.StarLordTheme
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.koin.androidx.compose.koinViewModel
import java.util.Date

public typealias OnDetailUiEvent = (DetailUiEvent) -> Unit

@Composable
public fun DetailScreenStateOwner(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    when (val state = uiState) {
        DetailUiState.Uninitialized,
        DetailUiState.Loading,
            -> {
            Column(
                modifier = modifier
                    .testTag("DetailScreenLoading"),
            ) {
                ProgressMessage(
                    modifier = modifier,
                )
            }

            LaunchedEffect(Unit) {
                viewModel.onUiEvent(DetailUiEvent.GetRepositoryData)
            }
        }

        is DetailUiState.Loaded -> {
            DetailScreen(
                modifier = modifier
                    .testTag("DetailScreen"),
                repository = state.repository,
                onUiEvent = viewModel::onUiEvent,
            )
        }

        is DetailUiState.Error -> {
            DetailErrorMessage(
                modifier = modifier
                    .testTag("DetailErrorMessage"),
                onUiEvent = viewModel::onUiEvent,
            )
        }
    }
}

@Composable
private fun DetailScreen(
    modifier: Modifier = Modifier,
    repository: RepositoryVO,
    onUiEvent: OnDetailUiEvent = {},
) {
    DetailContent(
        modifier = modifier,
        repository = repository,
        onUiEvent = onUiEvent,
    )
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    repository: RepositoryVO,
    onUiEvent: OnDetailUiEvent = {},
) {
    with(LocalConfiguration.current) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            DetailPortrait(
                modifier = modifier
                    .testTag("DetailPortrait"),
                repository = repository,
                onUiEvent = onUiEvent,
            )
        } else {
            DetailLandscape(
                modifier = modifier
                    .testTag("DetailLandscape"),
                repository = repository,
                onUiEvent = onUiEvent,
            )
        }
    }
}

@Composable
private fun DetailPortrait(
    modifier: Modifier = Modifier,
    repository: RepositoryVO,
    onUiEvent: OnDetailUiEvent = {},
) {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                context = LocalContext.current,
            ).data(data = repository.userAvatar)
                .crossfade(true)
                .build(),
        )
        val state by painter.state.collectAsState()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            IconButton(onClick = {
                onUiEvent(DetailUiEvent.Back)
            }) {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_content_description),
                )
            }
            Header(
                modifier = Modifier.fillMaxWidth()
            )
        }

        RepositoryImage(
            name = repository.name,
            author = repository.author,
            imageState = state.toImageUiState(),
            modifier = modifier
                .testTag("RepositoryImage")
                .size(200.dp),
        )

        Text(
            text = stringResource(R.string.repository_name_and_author, repository.name, repository.author),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(start = 8.dp),
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = "⭐ ${repository.starCount}",
            )
            Text(
                text = "🍴 ${repository.forkCount}",
            )
            Text(
                text = "\uD83D\uDC41\uFE0F ${repository.watcherCount}",
            )
            Text(
                text = "⚠\uFE0F ${repository.issueCount}",
            )
        }

        HorizontalDivider()
        val scrollState = rememberScrollState()
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(scrollState),
        ) {
            if (!repository.language.isNullOrBlank()) {
                Text(
                    text = stringResource(R.string.repository_language_name, repository.language)
                )
            }
            if (!repository.licenseName.isNullOrBlank()) {
                Text(
                    text = stringResource(R.string.repository_license_name, repository.licenseName)
                )
            }
            Text(
                text = repository.description,
            )

            HorizontalDivider()

            Text(
                text = stringResource(R.string.repository_created_at, repository.createdAt),
            )
            Text(
                text = stringResource(R.string.repository_pushed_at, repository.pushedAt),
            )
            Text(
                text = stringResource(R.string.repository_updated_at, repository.updatedAt),
            )
        }
    }
}

@Composable
private fun DetailLandscape(
    modifier: Modifier = Modifier,
    repository: RepositoryVO,
    onUiEvent: OnDetailUiEvent = {},
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            IconButton(onClick = {
                onUiEvent(DetailUiEvent.Back)
            }) {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_content_description),
                )
            }

            Header(
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(
                        context = LocalContext.current,
                    ).data(data = repository.userAvatar)
                        .crossfade(true)
                        .build(),
                )
                val state by painter.state.collectAsState()

                RepositoryImage(
                    name = repository.name,
                    author = repository.author,
                    imageState = state.toImageUiState(),
                    modifier = modifier
                        .size(200.dp),
                )

                Text(
                    text = stringResource(R.string.repository_name_and_author, repository.name, repository.author),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .widthIn(max = 220.dp),
                )
            }

            Column {
                Row(
                    verticalAlignment = Alignment.Bottom,
                ) {

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "⭐ ${repository.starCount}",
                            )
                            Text(
                                text = "🍴 ${repository.forkCount}",
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "\uD83D\uDC41\uFE0F ${repository.watcherCount}",
                            )
                            Text(
                                text = "⚠\uFE0F ${repository.issueCount}",
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            if (!repository.language.isNullOrBlank()) {
                                Text(
                                    text = stringResource(R.string.repository_language_name, repository.language),
                                )
                            }
                            if (!repository.licenseName.isNullOrBlank()) {
                                Text(
                                    text = stringResource(R.string.repository_license_name, repository.licenseName),
                                )
                            }
                        }

                        Text(
                            text = stringResource(R.string.repository_created_at, repository.createdAt),
                            style = MaterialTheme.typography.bodySmall,
                        )

                        Text(
                            text = stringResource(R.string.repository_pushed_at, repository.pushedAt),
                            style = MaterialTheme.typography.bodySmall,
                        )
                        Text(
                            text = stringResource(R.string.repository_updated_at, repository.updatedAt),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                )

                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .verticalScroll(scrollState),
                ) {
                    Text(
                        text = repository.description,
                    )
                }
            }
        }
    }
}

@DevicePreview
@Composable
private fun DetailScreenPreview() {
    StarLordTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailContent(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
                repository = RepositoryVO(
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
}
