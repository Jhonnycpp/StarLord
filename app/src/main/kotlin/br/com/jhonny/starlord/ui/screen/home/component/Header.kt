package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.ui.ComponentPreview
import br.com.jhonny.starlord.ui.theme.StarLordTheme

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
    StarLordTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Header(
                modifier = Modifier
                    .padding(innerPadding),
            )
        }
    }
}
