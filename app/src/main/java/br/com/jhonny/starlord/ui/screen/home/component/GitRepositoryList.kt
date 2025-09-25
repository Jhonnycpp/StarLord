package br.com.jhonny.starlord.ui.screen.home.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.ui.ComponentPreview
import br.com.jhonny.starlord.ui.screen.home.provider.RepositoryPreviewProvider
import br.com.jhonny.starlord.ui.screen.home.vo.RepositoryVO
import br.com.jhonny.starlord.ui.theme.StarLordTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
internal fun GitRepositoryList(
    modifier: Modifier = Modifier,
    repositories: List<RepositoryVO>,
    onLoadMore: () -> Unit = {},
    onItemClick: (RepositoryVO) -> Unit = {},
) {
    val columnCount = with(LocalConfiguration.current) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
    }

    val gridState = rememberLazyStaggeredGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo }
            .map { layoutInfo ->
                val totalItems = layoutInfo.totalItemsCount
                val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisible to totalItems
            }
            .distinctUntilChanged()
            .collect { (lastVisible, totalItems) ->
                if (lastVisible >= totalItems - 10) {
                    onLoadMore()
                }
            }
    }

    LazyVerticalStaggeredGrid(
        state = gridState,
        columns = StaggeredGridCells.Fixed(columnCount),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(repositories) {
                GitRepositoryItem(
                    item = it,
                    modifier = Modifier
                        .testTag("GitRepositoryItem"),
                ) {
                    onItemClick(it)
                }
            }
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier
            .testTag("GitRepositoryList")
            .padding(top = 16.dp)
    )
}

@ComponentPreview
@Composable
private fun GitRepositoryListPreview(
    @PreviewParameter(RepositoryPreviewProvider::class) repositories: List<RepositoryVO>,
) {
    StarLordTheme {
        Scaffold { innerPadding ->
            GitRepositoryList(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
                repositories = repositories,
            )
        }
    }
}
