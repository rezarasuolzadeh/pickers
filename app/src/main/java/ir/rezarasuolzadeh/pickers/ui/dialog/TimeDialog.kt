package ir.rezarasuolzadeh.pickers.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ir.rezarasuolzadeh.pickers.ui.compose.time.TimePickerDialogCompose
import ir.rezarasuolzadeh.pickers.utils.enums.TimeOutputType
import ir.rezarasuolzadeh.pickers.utils.enums.TimeType
import ir.rezarasuolzadeh.pickers.utils.extensions.orZero
import ir.rezarasuolzadeh.pickers.utils.extensions.toast
import ir.rezarasuolzadeh.pickers.utils.managers.TimeValidationManager

@Composable
fun TimeDialog(
    title: String? = null,
    confirmTitle: String? = null,
    cancelTitle: String? = null,
    initialHour: Int? = null,
    initialMinute: Int? = null,
    initialSecond: Int? = null,
    initialTimeType: TimeType? = null,
    fontFamily: FontFamily? = null,
    is12Hour: Boolean = false,
    showSeconds: Boolean = true,
    titleColor: Color? = null,
    hourColor: Color? = null,
    minuteColor: Color? = null,
    secondColor: Color? = null,
    timeTypeColor: Color? = null,
    colonColor: Color? = null,
    confirmColor: Color? = null,
    cancelColor: Color? = null,
    dividerColor: Color? = null,
    backgroundColor: Color? = null,
    backgroundBrush: Brush? = null,
    outputType: TimeOutputType? = null,
    outputSeparator: Char? = null,
    onCancel: () -> Unit,
    onTimeSelect: (String) -> Unit
) {
    val context = LocalContext.current

    if (
        TimeValidationManager.isTimeValid(
            second = initialSecond.orZero(),
            minute = initialMinute.orZero(),
            hour = initialHour.orZero(),
            is12Hour = is12Hour
        )
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
                TimePickerDialogCompose(
                    title = title,
                    confirmTitle = confirmTitle,
                    cancelTitle = cancelTitle,
                    initialHour = initialHour,
                    initialMinute = initialMinute,
                    initialSecond = initialSecond,
                    initialTimeType = initialTimeType,
                    fontFamily = fontFamily,
                    is12Hour = is12Hour,
                    showSeconds = showSeconds,
                    titleColor = titleColor,
                    hourColor = hourColor,
                    minuteColor = minuteColor,
                    secondColor = secondColor,
                    timeTypeColor = timeTypeColor,
                    colonColor = colonColor,
                    confirmColor = confirmColor,
                    cancelColor = cancelColor,
                    dividerColor = dividerColor,
                    backgroundColor = backgroundColor,
                    backgroundBrush = backgroundBrush,
                    outputType = outputType,
                    outputSeparator = outputSeparator,
                    onTimeSelect = onTimeSelect,
                    onCancel = onCancel
                )
            }
        }
    } else {
        context.toast(message = "زمان اولیه نامعتبر می باشد")
    }
}

@Preview
@Composable
internal fun TimeDialogPreview() {
    TimeDialog(
        onCancel = {
            // nothing to do yet
        },
        onTimeSelect = {
            // nothing to do yet
        }
    )
}