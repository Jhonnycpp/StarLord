package br.com.jhonny.startlord.ui.screen.home.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.jhonny.startlord.ui.ComponentPreview
import br.com.jhonny.startlord.ui.screen.home.provider.RepositoryPreviewProvider
import br.com.jhonny.startlord.ui.screen.home.vo.RepositoryVO
import br.com.jhonny.startlord.ui.theme.StartLordTheme

@Composable
internal fun GitRepositoryList(
    modifier: Modifier = Modifier,
    repositories: List<RepositoryVO>,
) {
    val columnCount = with(LocalConfiguration.current) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) 4 else 2
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(columnCount),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(repositories) {
                GitRepositoryItem(item = it)
            }
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier.padding(top = 16.dp)
    )
}

@ComponentPreview
@Composable
private fun GitRepositoryListPreview(
    @PreviewParameter(RepositoryPreviewProvider::class) repositories: List<RepositoryVO>,
) {
    StartLordTheme {
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
