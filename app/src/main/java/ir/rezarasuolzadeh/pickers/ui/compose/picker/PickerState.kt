package ir.rezarasuolzadeh.pickers.ui.compose.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
internal fun rememberPickerState() = remember { PickerState() }

internal class PickerState {
    var selectedItem by mutableStateOf("")
}