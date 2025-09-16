package br.com.jhonny.startlord.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * This annotation is used to preview the screen in different configurations.
 *
 * For example, you can preview the screen in light and dark mode.
 * You can also preview the screen in different sizes and screen orientations.
 * ```
 * @DefaultPreview
 * @Composable
 * private fun SampleScreenPreview() {
 *     SampleTheme {
 *         SampleScreen()
 *     }
 * }
 * ```
 */
@Preview(
    showSystemUi = true,
    name = "Light Mode - Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showSystemUi = true,
    name = "Light Mode - Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    showSystemUi = true,
    name = "Dark Mode - Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    showSystemUi = true,
    name = "Dark Mode - Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
)
public annotation class DevicePreview

/**
 * This annotation is used to preview the screen in different configurations **without** system ui.
 *
 * For example, you can preview the screen in light and dark mode.
 * You can also preview the screen in different sizes and screen orientations.
 * ```
 * @DefaultPreview
 * @Composable
 * private fun SampleScreenPreview() {
 *     SampleTheme {
 *         SampleScreen()
 *     }
 * }
 * ```
 */
@Preview(
    name = "Light Mode - Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Light Mode - Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    name = "Dark Mode - Portrait",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Dark Mode - Landscape",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
)
public annotation class ComponentPreview
