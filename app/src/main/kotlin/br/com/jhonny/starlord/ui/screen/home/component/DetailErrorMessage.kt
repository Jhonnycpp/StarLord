package br.com.jhonny.starlord.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import br.com.jhonny.starlord.ui.screen.home.detail.OnDetailUiEvent
import br.com.jhonny.starlord.ui.screen.home.detail.state.DetailUiEvent

/**
 * A composable function that displays a generic error message for the detail screen.
 * It includes an error image, a descriptive text, a "Retry" button to re-fetch data,
 * and a "Back" button to navigate to the previous screen.
 *
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onUiEvent A lambda function that handles UI events, such as retrying the data fetch
 *                  or navigating back. It's triggered by the "Retry" and "Back" buttons.
 */
@Composable
public fun DetailErrorMessage(
    modifier: Modifier = Modifier,
    onUiEvent: OnDetailUiEvent = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .testTag("DetailErrorMessage")
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_android_error
                ),
                contentDescription = stringResource(R.string.error_image_content_description),
            )
            Text(
                text = stringResource(R.string.error_detail_message),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = { onUiEvent(DetailUiEvent.GetRepositoryData) },
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

            Button(
                onClick = { onUiEvent(DetailUiEvent.Back) },
                modifier = Modifier
                    .testTag("BackButton")
                    .padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.error_back_content_description),
                )
                Text(
                    text = stringResource(R.string.error_back_button),
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
        DetailErrorMessage(
            modifier = modifier,
        )
    }
}
