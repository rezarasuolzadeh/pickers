package ir.rezarasuolzadeh.pickers.ui.compose.date

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.rezarasuolzadeh.pickers.ui.theme.White
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.rezarasuolzadeh.pickers.ui.compose.picker.Picker
import ir.rezarasuolzadeh.pickers.ui.compose.picker.PickerState
import ir.rezarasuolzadeh.pickers.R
import ir.rezarasuolzadeh.pickers.utils.extensions.noRippleClickable
import ir.rezarasuolzadeh.pickers.ui.compose.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.DarkBlue
import ir.rezarasuolzadeh.pickers.ui.theme.LightBlue
import ir.rezarasuolzadeh.pickers.utils.enums.DateOutputType
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.utils.extensions.toNumericMonth
import ir.rezarasuolzadeh.pickers.viewmodels.date.DateEvent
import ir.rezarasuolzadeh.pickers.viewmodels.date.DateViewModel

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                            screen                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
internal fun DatePickerBottomSheetCompose(
    title: String?,
    confirmTitle: String?,
    cancelTitle: String?,
    initialDay: Int?,
    initialMonth: MonthType?,
    initialYear: Int?,
    yearRange: IntRange,
    fontFamily: FontFamily?,
    titleColor: Color?,
    yearColor: Color?,
    monthColor: Color?,
    dayColor: Color?,
    slashColor: Color?,
    confirmColor: Color?,
    cancelColor: Color?,
    dividerColor: Color?,
    backgroundColor: Color?,
    backgroundBrush: Brush?,
    outputSeparator: Char?,
    outputType: DateOutputType?,
    onDateSelect: (String) -> Unit,
    onCancel: () -> Unit,
    dateViewModel: DateViewModel = viewModel()
) {
    val years by dateViewModel.years.collectAsStateWithLifecycle()
    val months by dateViewModel.months.collectAsStateWithLifecycle()
    val days by dateViewModel.days.collectAsStateWithLifecycle()
    val yearPickerState = rememberPickerState()
    val monthPickerState = rememberPickerState()
    val dayPickerState = rememberPickerState()

    LaunchedEffect(key1 = Unit) {
        dateViewModel.apply {
            onEvent(
                event = DateEvent.SetYearRange(
                    yearRange = yearRange
                )
            )
            onEvent(
                event = DateEvent.SetInitialDate(
                    initialDay = initialDay,
                    initialMonth = initialMonth,
                    initialYear = initialYear,
                    yearRange = yearRange
                )
            )
        }
    }

    DatePickerBottomSheetComposeContent(
        title = title,
        confirmTitle = confirmTitle,
        cancelTitle = cancelTitle,
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
        years = years,
        months = months,
        days = days,
        yearPickerState = yearPickerState,
        monthPickerState = monthPickerState,
        dayPickerState = dayPickerState,
        onDayChanged = {
            dateViewModel.onEvent(
                DateEvent.UpdateSelectedDay(
                    selectedDay = it.toInt()
                )
            )
        },
        onMonthChanged = {
            dateViewModel.onEvent(
                DateEvent.UpdateDays(
                    selectedMonth = it
                )
            )
        },
        onYearChanged = { year ->
            dateViewModel.onEvent(
                DateEvent.CheckLeapYear(
                    year = year,
                    month = monthPickerState.selectedItem
                )
            )
        },
        onDateSelect = onDateSelect,
        onCancel = onCancel
    )
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           content                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
internal fun DatePickerBottomSheetComposeContent(
    title: String?,
    confirmTitle: String?,
    cancelTitle: String?,
    fontFamily: FontFamily?,
    titleColor: Color?,
    yearColor: Color?,
    monthColor: Color?,
    dayColor: Color?,
    slashColor: Color?,
    confirmColor: Color?,
    cancelColor: Color?,
    dividerColor: Color?,
    backgroundColor: Color?,
    backgroundBrush: Brush?,
    outputSeparator: Char?,
    outputType: DateOutputType?,
    years: List<String>,
    months: List<String>,
    days: List<String>,
    yearPickerState: PickerState,
    monthPickerState: PickerState,
    dayPickerState: PickerState,
    onDayChanged: (String) -> Unit,
    onMonthChanged: (String) -> Unit,
    onYearChanged: (String) -> Unit,
    onDateSelect: (String) -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = when {
            backgroundBrush == null && backgroundColor == null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topEnd = 15.dp, topStart = 15.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                LightBlue,
                                DarkBlue
                            )
                        )
                    )
            }
            backgroundBrush != null && backgroundColor == null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(brush = backgroundBrush)
            }
            backgroundBrush == null && backgroundColor != null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(color = backgroundColor)
            }
            backgroundBrush != null && backgroundColor != null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
                    .background(color = backgroundColor)
            }
            else -> Modifier
        }
    ) {
        Text(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
            text = title ?: "انتخاب تاریخ",
            color = titleColor ?: White,
            fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if(years.isNotEmpty()) {
                Picker(
                    state = yearPickerState,
                    items = years,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 90.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = yearColor ?: White,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onYearChanged
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "/",
                color = slashColor ?: White,
                fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            if(months.isNotEmpty()) {
                Picker(
                    state = monthPickerState,
                    items = months,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 90.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = monthColor ?: White,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onMonthChanged
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "/",
                color = slashColor ?: White,
                fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            if(days.isNotEmpty()) {
                Picker(
                    state = dayPickerState,
                    items = days,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 90.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = dayColor ?: White,
                    startIndex = 0,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onDayChanged
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .height(height = 0.5.dp)
                .fillMaxWidth()
                .background(color = dividerColor ?: White)
        )
        ConstraintLayout(
            modifier = Modifier
                .height(height = 50.dp)
                .fillMaxWidth()
        ) {
            val (confirm, cancel, divider) = createRefs()
            Box(
                modifier = Modifier
                    .width(width = 60.dp)
                    .height(height = 50.dp)
                    .constrainAs(ref = confirm) {
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = divider.start)
                        bottom.linkTo(anchor = parent.bottom)
                        top.linkTo(anchor = parent.top)
                    }
                    .noRippleClickable {
                        onCancel()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = cancelTitle ?: "انصراف",
                    color = cancelColor ?: White,
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(height = 50.dp)
                    .width(width = 0.5.dp)
                    .background(color = dividerColor ?: White)
                    .constrainAs(ref = divider) {
                        start.linkTo(anchor = parent.start)
                        end.linkTo(anchor = parent.end)
                        bottom.linkTo(anchor = parent.bottom)
                        top.linkTo(anchor = parent.top)
                    }
            )
            Box(
                modifier = Modifier
                    .width(width = 60.dp)
                    .height(height = 50.dp)
                    .constrainAs(ref = cancel) {
                        end.linkTo(anchor = parent.end)
                        start.linkTo(anchor = divider.end)
                        bottom.linkTo(anchor = parent.bottom)
                        top.linkTo(anchor = parent.top)
                    }
                    .noRippleClickable {
                        when (outputType) {
                            DateOutputType.PERSIAN -> {
                                onDateSelect("${yearPickerState.selectedItem}${outputSeparator ?: "/"}${monthPickerState.selectedItem}${outputSeparator ?: "/"}${dayPickerState.selectedItem}")
                            }

                            DateOutputType.NUMERIC -> {
                                onDateSelect("${yearPickerState.selectedItem}${outputSeparator ?: "/"}${monthPickerState.selectedItem.toNumericMonth()}${outputSeparator ?: "/"}${dayPickerState.selectedItem}")
                            }

                            else -> {
                                onDateSelect("${yearPickerState.selectedItem}${outputSeparator ?: "/"}${monthPickerState.selectedItem}${outputSeparator ?: "/"}${dayPickerState.selectedItem}")
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentSize(),
                    text = confirmTitle ?: "ثبت",
                    color = confirmColor ?: White,
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           preview                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Preview
@Composable
internal fun DatePickerBottomSheetPreview() {
    DatePickerBottomSheetCompose(
        title = null,
        confirmTitle = null,
        cancelTitle = null,
        initialDay = null,
        initialMonth = null,
        initialYear = null,
        yearRange = 1380..1410,
        fontFamily = null,
        titleColor = null,
        yearColor = null,
        monthColor = null,
        dayColor = null,
        slashColor = null,
        confirmColor = null,
        cancelColor = null,
        dividerColor = null,
        backgroundColor = null,
        backgroundBrush = null,
        outputSeparator = null,
        outputType = null,
        onDateSelect = {
            // nothing to do yet
        },
        onCancel = {
            // nothing to do yet
        }
    )
}