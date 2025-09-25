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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.jhonny.starlord.R
import br.com.jhonny.starlord.ui.ComponentPreview
import br.com.jhonny.starlord.ui.screen.home.list.OnHomeUiEvent
import br.com.jhonny.starlord.ui.screen.home.list.state.HomeUiEvent
import br.com.jhonny.starlord.ui.theme.StarLordTheme

@Composable
public fun ErrorMessage(
    modifier: Modifier = Modifier,
    onUiEvent: OnHomeUiEvent = {},
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
                contentDescription = "Error loading image."
            )
            Text(
                text = "An error occurred on fetch the git repositories.",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = {
                    onUiEvent(HomeUiEvent.RequestMoreData)
                },
                modifier = Modifier
                    .testTag("RetryButton")
                    .padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Retry fetch git repositories."
                )
                Text(
                    text = "Retry",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun ErrorMessagePreview() {
    StarLordTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ErrorMessage(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
            ) {

            }
        }
    }
}
