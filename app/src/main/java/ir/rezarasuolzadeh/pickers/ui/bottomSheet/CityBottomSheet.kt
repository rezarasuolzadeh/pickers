package ir.rezarasuolzadeh.pickers.ui.bottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import ir.rezarasuolzadeh.pickers.ui.compose.city.CityPickerBottomSheetCompose
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBottomSheet(
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
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    CompositionLocalProvider(value = LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalBottomSheet(
            onDismissRequest = { scope.launch { sheetState.hide() } },
            sheetState = sheetState,
            dragHandle = null
        ) {
            CityPickerBottomSheetCompose(
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
internal fun CityBottomSheetPreview() {
    CityBottomSheet(
        onCancel = {
            // nothing to do yet
        },
        onCitySelect = {
            // nothing to do yet
        }
    )
}