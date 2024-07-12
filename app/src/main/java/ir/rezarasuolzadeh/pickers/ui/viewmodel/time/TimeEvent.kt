package ir.rezarasuolzadeh.pickers.ui.viewmodel.time

import ir.rezarasuolzadeh.pickers.ui.utility.enums.TimeType

sealed class TimeEvent {
    data object UpdateCurrentTime: TimeEvent()
    data class SetTimeType(val timeType: TimeType): TimeEvent()
    data class SetInitTime(val initialHour: Int? = null, val initialMinute: Int? = null, val initialSecond: Int? = null): TimeEvent()
}