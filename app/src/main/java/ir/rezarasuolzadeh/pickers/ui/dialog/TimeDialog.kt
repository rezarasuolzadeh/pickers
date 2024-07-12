package ir.rezarasuolzadeh.pickers.ui.dialog

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.TimePickerDialogCompose
import ir.rezarasuolzadeh.pickers.ui.utility.extensions.orZero
import ir.rezarasuolzadeh.pickers.ui.utility.extensions.toast
import ir.rezarasuolzadeh.pickers.ui.utility.manager.ValidationManager

@Composable
fun TimeDialog(
    initialHour: Int? = null,
    initialMinute: Int? = null,
    initialSecond: Int? = null,
    is12Hour: Boolean = false,
    showSeconds: Boolean = true
) {
    val context = LocalContext.current

    if (
        ValidationManager.isTimeValid(
            second = initialSecond.orZero(),
            minute = initialMinute.orZero(),
            hour = initialHour.orZero(),
            is12Hour = is12Hour
        )
    ) {
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
                initialHour = initialHour,
                initialMinute = initialMinute,
                initialSecond = initialSecond,
                is12Hour = is12Hour,
                showSeconds = showSeconds,
                onTimeSelect = { selectedTime ->
                    Toast.makeText(context, selectedTime, Toast.LENGTH_SHORT).show()
                },
                onCancel = {}
            )
        }
    } else {
        context.toast(message = "زمان اولیه نامعتبر می باشد")
    }
}

@Preview
@Composable
fun TimeDialogPreview() {
    TimeDialog()
}