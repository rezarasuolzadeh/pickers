package ir.rezarasuolzadeh.pickers.ui.bottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import ir.rezarasuolzadeh.pickers.ui.compose.date.DatePickerBottomSheetCompose
import ir.rezarasuolzadeh.pickers.utils.enums.DateOutputType
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.orOne
import ir.rezarasuolzadeh.pickers.utils.extensions.toast
import ir.rezarasuolzadeh.pickers.utils.managers.DateValidationManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(
    title: String? = null,
    confirmTitle: String? = null,
    cancelTitle: String? = null,
    initialDay: Int? = null,
    initialMonth: MonthType? = null,
    initialYear: Int? = null,
    yearRange: IntRange = 1380..1410,
    fontFamily: FontFamily? = null,
    titleColor: Color? = null,
    yearColor: Color? = null,
    monthColor: Color? = null,
    dayColor: Color? = null,
    slashColor: Color? = null,
    confirmColor: Color? = null,
    cancelColor: Color? = null,
    dividerColor: Color? = null,
    backgroundColor: Color? = null,
    backgroundBrush: Brush? = null,
    outputType: DateOutputType? = null,
    outputSeparator: Char? = null,
    onCancel: () -> Unit,
    onDateSelect: (String) -> Unit
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
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()

        ModalBottomSheet(
            onDismissRequest = { scope.launch { sheetState.hide() } },
            sheetState = sheetState,
            dragHandle = null
        ) {
            DatePickerBottomSheetCompose(
                title = title,
                confirmTitle = confirmTitle,
                cancelTitle = cancelTitle,
                initialDay = initialDay,
                initialMonth = initialMonth,
                initialYear = initialYear,
                yearRange = yearRange,
                fontFamily = fontFamily,
                titleColor = titleColor,
                yearColor = yearColor,
                monthColor = monthColor,
                dayColor = dayColor,
                slashColor = slashColor,
                confirmColor = confirmColor,
                cancelColor = cancelColor,
                dividerColor = dividerColor,
                backgroundColor = backgroundColor,
                backgroundBrush = backgroundBrush,
                outputSeparator = outputSeparator,
                outputType = outputType,
                onCancel = onCancel,
                onDateSelect = onDateSelect
            )
        }
    } else {
        context.toast(message = "تاریخ اولیه نامعتبر می باشد")
    }
}

@Preview
@Composable
internal fun DateBottomSheetPreview() {
    DateBottomSheet(
        onCancel = {
            // nothing to do yet
        },
        onDateSelect = {
            // nothing to do yet
        }
    )
}