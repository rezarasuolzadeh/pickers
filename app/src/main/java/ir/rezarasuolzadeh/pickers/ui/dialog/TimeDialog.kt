package ir.rezarasuolzadeh.pickers.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.TimePickerDialogCompose

@Composable
fun TimeDialog() {
    Dialog(
        onDismissRequest = {
            // nothing to do yet
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        TimePickerDialogCompose(
            onTimeSelect = {
                // nothing to do yet
            }
        )
    }
}