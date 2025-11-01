package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.ui.preview.ComponentPreview
import br.com.jhonny.starlord.ui.preview.PreviewContentRender

/**
 * A composable that displays a generic error message screen.
 * It includes an error icon, a message, and a "Retry" button.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param onRetry A lambda function to be executed when the "Retry" button is clicked.
 */
@Composable
public fun ErrorMessage(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .testTag("ErrorMessage")
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_android_error
                ),
                contentDescription = stringResource(R.string.error_image_content_description),
            )
            Text(
                text = stringResource(R.string.error_home_retry),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = onRetry,
                modifier = Modifier
                    .testTag("RetryButton")
                    .padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.error_detail_retry_content_description),
                )
                Text(
                    text = stringResource(R.string.error_retry_button),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun ErrorMessagePreview() {
    PreviewContentRender { modifier ->
        ErrorMessage(
            modifier = modifier,
        ) {

        }
    }
}
