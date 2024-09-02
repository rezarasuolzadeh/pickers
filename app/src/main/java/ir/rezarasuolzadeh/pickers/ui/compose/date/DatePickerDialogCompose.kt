package ir.rezarasuolzadeh.pickers.ui.compose.date

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import ir.rezarasuolzadeh.pickers.utils.enums.MonthType
import ir.rezarasuolzadeh.pickers.viewmodels.date.DateEvent
import ir.rezarasuolzadeh.pickers.viewmodels.date.DateViewModel

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                            screen                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun DatePickerDialogCompose(
    initialDay: Int?,
    initialMonth: MonthType?,
    initialYear: Int?,
    yearRange: IntRange,
    onDateSelect: (String) -> Unit,
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

    DatePickerDialogComposeContent(
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
        onDateSelect = onDateSelect
    )
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           content                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun DatePickerDialogComposeContent(
    years: List<String>,
    months: List<String>,
    days: List<String>,
    yearPickerState: PickerState,
    monthPickerState: PickerState,
    dayPickerState: PickerState,
    onDayChanged: (String) -> Unit,
    onMonthChanged: (String) -> Unit,
    onYearChanged: (String) -> Unit,
    onDateSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightBlue,
                        DarkBlue
                    )
                )
            )
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            text = "انتخاب تاریخ",
            color = White,
            fontFamily = FontFamily(Font(R.font.vazir_num)),
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
                        .width(90.dp),
                    textModifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onYearChanged
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "/",
                color = White,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            if(years.isNotEmpty()) {
                Picker(
                    state = monthPickerState,
                    items = months,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(90.dp),
                    textModifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onMonthChanged
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "/",
                color = White,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
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
                        .width(90.dp),
                    startIndex = 0,
                    textModifier = Modifier.padding(8.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    onItemChanged = onDayChanged
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 25.dp)
                .height(height = 0.3.dp)
                .fillMaxWidth()
                .background(color = White)
        )
        ConstraintLayout(
            modifier = Modifier
                .height(height = 50.dp)
                .fillMaxWidth()
        ) {
            val (confirm, cancel, divider) = createRefs()
            Text(
                modifier = Modifier
                    .width(width = 60.dp)
                    .height(height = 50.dp)
                    .padding(top = 5.dp)
                    .constrainAs(confirm) {
                        start.linkTo(parent.start)
                        end.linkTo(divider.start)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
                    .noRippleClickable {
                        // nothing to do yet
                    },
                text = "انصراف",
                color = White,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(height = 50.dp)
                    .width(width = 0.3.dp)
                    .background(color = White)
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                modifier = Modifier
                    .width(width = 60.dp)
                    .height(height = 50.dp)
                    .padding(top = 5.dp)
                    .constrainAs(cancel) {
                        end.linkTo(parent.end)
                        start.linkTo(divider.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                    }
                    .noRippleClickable {
                        onDateSelect("${yearPickerState.selectedItem}/${monthPickerState.selectedItem}/${dayPickerState.selectedItem}")
                    },
                text = "ثبت",
                color = White,
                fontFamily = FontFamily(Font(R.font.vazir_num)),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           preview                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Preview
@Composable
fun DatePickerDialogPreview() {
    DatePickerDialogCompose(
        initialDay = null,
        initialMonth = null,
        initialYear = null,
        yearRange = 1380..1410,
        onDateSelect = {
            // nothing to do yet
        }
    )
}