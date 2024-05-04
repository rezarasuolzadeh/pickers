package ir.rezarasuolzadeh.pickers.ui.dialog

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.DatePickerDialogCompose

@Composable
fun DateDialog() {
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
        DatePickerDialogCompose(
            onDateSelect = { selectedDate ->
                Toast.makeText(context, selectedDate, Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Preview
@Composable
fun DateDialogPreview() {
    DateDialog()
}