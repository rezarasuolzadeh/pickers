package ir.rezarasuolzadeh.pickers.ui.dialog

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.TimePickerDialogCompose

@Composable
fun TimeDialog() {
    val context = LocalContext.current

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
            showSeconds = true,
            onTimeSelect = { selectedTime ->
                Toast.makeText(context, selectedTime, Toast.LENGTH_SHORT).show()
            },
            onCancel = {}
        )
    }
}

@Preview
@Composable
fun TimeDialogPreview() {
    TimeDialog()
}