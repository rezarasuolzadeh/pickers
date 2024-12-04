package ir.rezarasuolzadeh.pickers.ui.compose.time

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.rezarasuolzadeh.pickers.ui.compose.picker.Picker
import ir.rezarasuolzadeh.pickers.R
import ir.rezarasuolzadeh.pickers.utils.extensions.noRippleClickable
import ir.rezarasuolzadeh.pickers.ui.compose.picker.rememberPickerState
import ir.rezarasuolzadeh.pickers.ui.theme.DarkBlue
import ir.rezarasuolzadeh.pickers.ui.theme.Gray
import ir.rezarasuolzadeh.pickers.ui.theme.LightBlue
import ir.rezarasuolzadeh.pickers.ui.theme.White
import ir.rezarasuolzadeh.pickers.utils.enums.TimeOutputType
import ir.rezarasuolzadeh.pickers.utils.enums.TimeType
import ir.rezarasuolzadeh.pickers.viewmodels.time.TimeEvent
import ir.rezarasuolzadeh.pickers.viewmodels.time.TimeViewModel

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                         screen                                                 //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
internal fun TimePickerDialogCompose(
    title: String?,
    confirmTitle: String?,
    cancelTitle: String?,
    initialHour: Int?,
    initialMinute: Int?,
    initialSecond: Int?,
    initialTimeType: TimeType?,
    fontFamily: FontFamily?,
    is12Hour: Boolean,
    showSeconds: Boolean,
    titleColor: Color?,
    hourColor: Color?,
    minuteColor: Color?,
    secondColor: Color?,
    timeTypeColor: Color?,
    colonColor: Color?,
    confirmColor: Color?,
    cancelColor: Color?,
    dividerColor: Color?,
    backgroundColor: Color?,
    backgroundBrush: Brush?,
    outputType: TimeOutputType?,
    outputSeparator: Char?,
    onTimeSelect: (String) -> Unit,
    onCancel: () -> Unit,
    timeViewModel: TimeViewModel = viewModel()
) {
    val timeType by timeViewModel.timeType.collectAsStateWithLifecycle()
    val hours by timeViewModel.hours.collectAsStateWithLifecycle()
    val minutes by timeViewModel.minutes.collectAsStateWithLifecycle()
    val seconds by timeViewModel.seconds.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        timeViewModel.apply {
            onEvent(
                event = TimeEvent.SetTimeFormat(
                    is12Hour = is12Hour
                )
            )
            onEvent(
                event = TimeEvent.SetInitialTime(
                    initialHour = initialHour,
                    initialMinute = initialMinute,
                    initialSecond = initialSecond,
                    initialTimeType = initialTimeType
                )
            )
        }
    }

    TimePickerDialogComposeContent(
        title = title,
        confirmTitle = confirmTitle,
        cancelTitle = cancelTitle,
        hours = hours,
        minutes = minutes,
        seconds = seconds,
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
        timeType = timeType,
        onTimeSelect = onTimeSelect,
        onCancel = onCancel,
        onEvent = {
            timeViewModel.onEvent(event = it)
        }
    )
}

////////////////////////////////////////////////////////////////////////////////////////////////////
//                                           content                                              //
////////////////////////////////////////////////////////////////////////////////////////////////////

@Composable
internal fun TimePickerDialogComposeContent(
    title: String?,
    confirmTitle: String?,
    cancelTitle: String?,
    hours: List<String>,
    minutes: List<String>,
    seconds: List<String>,
    fontFamily: FontFamily?,
    is12Hour: Boolean,
    showSeconds: Boolean,
    titleColor: Color?,
    hourColor: Color?,
    minuteColor: Color?,
    secondColor: Color?,
    timeTypeColor: Color?,
    colonColor: Color?,
    confirmColor: Color?,
    cancelColor: Color?,
    dividerColor: Color?,
    backgroundColor: Color?,
    backgroundBrush: Brush?,
    outputType: TimeOutputType?,
    outputSeparator: Char?,
    timeType: TimeType,
    onTimeSelect: (String) -> Unit,
    onCancel: () -> Unit,
    onEvent: (TimeEvent) -> Unit
) {
    Column(
        modifier = when {
            backgroundBrush == null && backgroundColor == null -> {
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(size = 15.dp))
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
        val hourPickerState = rememberPickerState()
        val minutePickerState = rememberPickerState()
        val secondPickerState = rememberPickerState()
        Text(
            modifier = Modifier
                .padding(top = 18.dp)
                .fillMaxWidth(),
            text = title ?: "انتخاب زمان",
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
            if (hours.isNotEmpty()) {
                Picker(
                    state = hourPickerState,
                    items = hours,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 65.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = hourColor ?: White,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 20.sp)
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = ":",
                color = colonColor ?: White,
                fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                fontWeight = FontWeight.ExtraBold
            )
            if (minutes.isNotEmpty()) {
                Picker(
                    state = minutePickerState,
                    items = minutes,
                    visibleItemsCount = 3,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .width(width = 65.dp),
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    itemColor = minuteColor ?: White,
                    textModifier = Modifier.padding(all = 8.dp),
                    textStyle = TextStyle(fontSize = 20.sp)
                )
            }
            if (showSeconds) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    text = ":",
                    color = colonColor ?: White,
                    fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                    fontWeight = FontWeight.ExtraBold
                )
                if (seconds.isNotEmpty()) {
                    Picker(
                        state = secondPickerState,
                        items = seconds,
                        visibleItemsCount = 3,
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(width = 65.dp),
                        fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir_num)),
                        itemColor = secondColor ?: White,
                        textModifier = Modifier.padding(all = 8.dp),
                        textStyle = TextStyle(fontSize = 20.sp)
                    )
                }
            }
            if (is12Hour) {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .height(height = 60.dp)
                        .width(width = 45.dp)
                        .border(
                            width = 0.5.dp,
                            color = timeTypeColor ?: White,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                ) {
                    Text(
                        modifier = if (timeType == TimeType.AM) {
                            Modifier
                                .width(width = 45.dp)
                                .height(height = 30.dp)
                                .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                                .background(
                                    color = timeTypeColor?.copy(alpha = 0.3f) ?: White.copy(
                                        alpha = 0.3f
                                    )
                                )
                                .noRippleClickable {
                                    onEvent(
                                        TimeEvent.SetTimeType(
                                            timeType = TimeType.AM
                                        )
                                    )
                                }
                        } else {
                            Modifier
                                .width(width = 45.dp)
                                .height(height = 30.dp)
                                .noRippleClickable {
                                    onEvent(
                                        TimeEvent.SetTimeType(
                                            timeType = TimeType.AM
                                        )
                                    )
                                }
                        },
                        text = "ق.ظ",
                        color = if (timeType == TimeType.AM) timeTypeColor ?: White else Gray,
                        fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir)),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp
                    )
                    Box(
                        modifier = Modifier
                            .height(height = 0.5.dp)
                            .fillMaxWidth()
                            .background(color = timeTypeColor ?: White)
                    )
                    Text(
                        modifier = if (timeType == TimeType.PM) {
                            Modifier
                                .width(width = 45.dp)
                                .height(height = 30.dp)
                                .clip(
                                    shape = RoundedCornerShape(
                                        bottomStart = 10.dp,
                                        bottomEnd = 10.dp
                                    )
                                )
                                .background(
                                    color = timeTypeColor?.copy(alpha = 0.3f) ?: White.copy(
                                        alpha = 0.3f
                                    )
                                )
                                .noRippleClickable {
                                    onEvent(
                                        TimeEvent.SetTimeType(
                                            timeType = TimeType.PM
                                        )
                                    )
                                }
                        } else {
                            Modifier
                                .width(width = 45.dp)
                                .height(height = 30.dp)
                                .noRippleClickable {
                                    onEvent(
                                        TimeEvent.SetTimeType(
                                            timeType = TimeType.PM
                                        )
                                    )
                                }
                        },
                        text = "ب.ظ",
                        color = if (timeType == TimeType.PM) timeTypeColor ?: White else Gray,
                        fontFamily = fontFamily ?: FontFamily(Font(resId = R.font.vazir)),
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp
                    )
                }
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
                        if (showSeconds) {
                            if (is12Hour) {
                                when (outputType) {
                                    TimeOutputType.PERSIAN -> {
                                        onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${secondPickerState.selectedItem}${outputSeparator ?: ":"}${timeType.persianTitle}")
                                    }

                                    TimeOutputType.ENGLISH -> {
                                        onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${secondPickerState.selectedItem}${outputSeparator ?: ":"}${timeType.englishTitle}")
                                    }

                                    else -> {
                                        onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${secondPickerState.selectedItem}${outputSeparator ?: ":"}${timeType.englishTitle}")
                                    }
                                }
                            } else {
                                onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${secondPickerState.selectedItem}")
                            }
                        } else {
                            if (is12Hour) {
                                when (outputType) {
                                    TimeOutputType.PERSIAN -> {
                                        onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${timeType.persianTitle}")
                                    }

                                    TimeOutputType.ENGLISH -> {
                                        onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${timeType.englishTitle}")
                                    }

                                    else -> {
                                        onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}${outputSeparator ?: ":"}${timeType.englishTitle}")
                                    }
                                }
                            } else {
                                onTimeSelect("${hourPickerState.selectedItem}${outputSeparator ?: ":"}${minutePickerState.selectedItem}")
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
internal fun TimePickerDialogPreview() {
    TimePickerDialogCompose(
        title = null,
        confirmTitle = null,
        cancelTitle = null,
        initialHour = null,
        initialMinute = null,
        initialSecond = null,
        initialTimeType = null,
        fontFamily = null,
        is12Hour = false,
        showSeconds = true,
        titleColor = null,
        hourColor = null,
        minuteColor = null,
        secondColor = null,
        timeTypeColor = null,
        colonColor = null,
        confirmColor = null,
        cancelColor = null,
        dividerColor = null,
        backgroundColor = null,
        backgroundBrush = null,
        outputType = null,
        outputSeparator = null,
        onTimeSelect = {
            // nothing to do yet
        },
        onCancel = {
            // nothing to do yet
        }
    )
}