package ir.rezarasuolzadeh.pickers.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.ui.compose.city.CityPickerDialogCompose

@Composable
fun CityDialog(
    title: String? = null,
    confirmTitle: String? = null,
    cancelTitle: String? = null,
    fontFamily: FontFamily? = null,
    titleColor: Color? = null,
    confirmColor: Color? = null,
    cancelColor: Color? = null,
    dividerColor: Color? = null,
    backgroundColor: Color? = null,
    backgroundBrush: Brush? = null,
    outputSeparator: Char? = null,
    onCancel: () -> Unit,
    onCitySelect: (String) -> Unit
) {
    CompositionLocalProvider(value = LocalLayoutDirection provides LayoutDirection.Rtl) {
        Dialog(
            onDismissRequest = {
                // nothing to do yet
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            CityPickerDialogCompose(
                title = title,
                confirmTitle = confirmTitle,
                cancelTitle = cancelTitle,
                fontFamily = fontFamily,
                titleColor = titleColor,
                confirmColor = confirmColor,
                cancelColor = cancelColor,
                dividerColor = dividerColor,
                backgroundColor = backgroundColor,
                backgroundBrush = backgroundBrush,
                outputSeparator = outputSeparator,
                onCitySelect = onCitySelect,
                onCancel = onCancel
            )
        }
    }
}

@Preview
@Composable
internal fun CityDialogPreview() {
    CityDialog(
        onCancel = {
            // nothing to do yet
        },
        onCitySelect = {
            // nothing to do yet
        }
    )
}