package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender

@Composable
public fun LoadingOverlay(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Header()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .testTag("LoadingIndicator")
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@ComponentPreview
@Composable
private fun LoadingOverlayPreview() {
    PreviewContentRender { modifier ->
        LoadingOverlay(
            modifier = modifier,
        )
    }
}
