package br.com.jhonny.starlord.ui.preview

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.jhonny.starlord.ui.theme.StarLordTheme

/**
 * A composable function that wraps content in a standard preview layout.
 * It applies the [StarLordTheme] and a [Scaffold] to provide a consistent
 * look and feel for previews, handling inner padding automatically.
 *
 * This is useful for creating previews of individual components or screens
 * without duplicating the theme and scaffold setup.
 *
 * @param content The composable content to be rendered inside the scaffold.
 *                It receives a [Modifier] with the appropriate padding
 *                applied by the [Scaffold].
 */
@Composable
public fun PreviewContentRender(
    content: @Composable (Modifier) -> Unit,
) {
    StarLordTheme {
        Scaffold { innerPadding ->
            content(Modifier.padding(innerPadding))
        }
    }
}
