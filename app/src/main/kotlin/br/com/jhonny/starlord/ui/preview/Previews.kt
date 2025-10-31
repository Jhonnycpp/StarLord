package br.com.jhonny.starlord.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/**
 * This annotation is used to preview the screen in different configurations.
 *
 * For example, you can preview the screen in light and dark mode.
 * You can also preview the screen in different sizes, screen orientations and languages.
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
    name = "Light Mode - Portrait - US",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showSystemUi = true,
    name = "Light Mode - Portrait - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "pt-rBR",
)
@Preview(
    showSystemUi = true,
    name = "Light Mode - Landscape - US",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    showSystemUi = true,
    name = "Light Mode - Landscape - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_5,orientation=landscape",
    locale = "pt-rBR",
)
@Preview(
    showSystemUi = true,
    name = "Dark Mode - Portrait - US",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    showSystemUi = true,
    name = "Dark Mode - Portrait - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "pt-rBR",
)
@Preview(
    showSystemUi = true,
    name = "Dark Mode - Landscape - US",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    showSystemUi = true,
    name = "Dark Mode - Landscape - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
    locale = "pt-rBR",
)
public annotation class DevicePreview

/**
 * This annotation is used to preview the screen in different configurations **without** system ui.
 *
 * For example, you can preview the screen in light and dark mode.
 * You can also preview the screen in different sizes, screen orientations and languages.
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
    name = "Light Mode - Portrait - US",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Light Mode - Portrait - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    locale = "pt-rBR",
)
@Preview(
    name = "Light Mode - Landscape - US",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    name = "Light Mode - Landscape - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = "spec:parent=pixel_5,orientation=landscape",
    locale = "pt-rBR",
)
@Preview(
    name = "Dark Mode - Portrait - US",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Dark Mode - Portrait - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    locale = "pt-rBR",
)
@Preview(
    name = "Dark Mode - Landscape - US",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Preview(
    name = "Dark Mode - Landscape - PT-BR",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    device = "spec:parent=pixel_5,orientation=landscape",
    locale = "pt-rBR",
)
public annotation class ComponentPreview
