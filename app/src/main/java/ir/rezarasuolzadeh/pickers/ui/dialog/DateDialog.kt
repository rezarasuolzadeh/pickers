package ir.rezarasuolzadeh.pickers.ui.dialog

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.ui.compose.date.DatePickerDialogCompose
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.orOne
import ir.rezarasuolzadeh.pickers.utils.extensions.toast
import ir.rezarasuolzadeh.pickers.utils.managers.DateValidationManager

@Composable
fun DateDialog(
    initialDay: Int? = null,
    initialMonth: MonthType? = null,
    initialYear: Int? = null,
    yearRange: IntRange = 1380..1410
) {
    val context = LocalContext.current

    if (
        DateValidationManager.isDateValid(
            day = initialDay.orOne(),
            month = initialMonth ?: MonthType.FARVARDIN,
            year = initialYear ?: yearRange.first,
            yearRange = yearRange
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
            DatePickerDialogCompose(
                initialDay = initialDay,
                initialMonth = initialMonth,
                initialYear = initialYear,
                yearRange = yearRange,
                onDateSelect = { selectedDate ->
                    Toast.makeText(context, selectedDate, Toast.LENGTH_SHORT).show()
                }
            )
        }
    } else {
        context.toast(message = "تاریخ اولیه نامعتبر می باشد")
    }
}

@Preview
@Composable
fun DateDialogPreview() {
    DateDialog(
        yearRange = 1380..1410
    )
}