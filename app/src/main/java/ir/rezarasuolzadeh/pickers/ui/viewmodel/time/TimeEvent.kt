package ir.rezarasuolzadeh.pickers.ui.viewmodel.time

import ir.rezarasuolzadeh.pickers.ui.utility.enums.TimeType

sealed class TimeEvent {
    data class SetTimeFormat(val is12Hour: Boolean): TimeEvent()
    data class SetTimeType(val timeType: TimeType): TimeEvent()
    data class SetInitialTime(val initialHour: Int? = null, val initialMinute: Int? = null, val initialSecond: Int? = null): TimeEvent()
}