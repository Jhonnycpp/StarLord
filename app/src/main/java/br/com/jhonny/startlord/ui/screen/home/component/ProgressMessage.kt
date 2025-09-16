package br.com.jhonny.startlord.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.jhonny.startlord.ui.ComponentPreview
import br.com.jhonny.startlord.ui.screen.home.Header
import br.com.jhonny.startlord.ui.theme.StartLordTheme

@Composable
public fun ProgressMessage(
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
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@ComponentPreview
@Composable
private fun ProgressMessagePreview() {
    StartLordTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ProgressMessage(
                modifier = Modifier.padding(
                    paddingValues = innerPadding
                ),
            )
        }
    }
}
