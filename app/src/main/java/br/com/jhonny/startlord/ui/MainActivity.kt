package br.com.jhonny.startlord.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.jhonny.startlord.ui.screen.home.HomeScreeStateOwner
import br.com.jhonny.startlord.ui.theme.StartLordTheme

public class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StartLordTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreeStateOwner(
                        modifier = Modifier.padding(
                            paddingValues = innerPadding
                        )
                    )
                }
            }
        }
    }
}
