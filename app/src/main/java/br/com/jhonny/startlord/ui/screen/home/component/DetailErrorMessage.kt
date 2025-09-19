package br.com.jhonny.startlord.ui.screen.home.component

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.jhonny.startlord.R
import br.com.jhonny.startlord.ui.ComponentPreview
import br.com.jhonny.startlord.ui.screen.home.detail.OnDetailUiEvent
import br.com.jhonny.startlord.ui.screen.home.detail.state.DetailUiEvent
import br.com.jhonny.startlord.ui.theme.StartLordTheme

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
                onClick = { onUiEvent(DetailUiEvent.GetRepositoryData) },
                modifier = Modifier.padding(top = 32.dp)
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

            Button(
                onClick = { onUiEvent(DetailUiEvent.Back) },
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to home."
                )
                Text(
                    text = "Back",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun ErrorMessagePreview() {
    StartLordTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailErrorMessage(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
            )
        }
    }
}
