package br.com.jhonny.starlord.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.jhonny.starlord.ui.navigation.createNavGraph
import br.com.jhonny.starlord.ui.theme.StarLordTheme

public class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarLordTheme {
                val navController: NavHostController = rememberNavController()
                val navGraph = remember { navController.createNavGraph() }

                AppContent(
                    navController = navController,
                    navGraph = navGraph,
                )
            }
        }
    }
}
