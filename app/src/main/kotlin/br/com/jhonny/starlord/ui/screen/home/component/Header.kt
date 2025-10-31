package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender

@Composable
internal fun Header(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.header_title),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = modifier.testTag("Header"),
    )
}

@ComponentPreview
@Composable
private fun HeaderPreview() {
    PreviewContentRender { modifier ->
        Header(
            modifier = modifier,
        )
    }
}
