package ir.rezarasuolzadeh.pickers.viewmodels.date

import ir.rezarasuolzadeh.pickers.utils.enums.MonthType

sealed class DateEvent {
    data class SetYearRange(val yearRange: IntRange) : DateEvent()
    data class OnUpdateDays(val selectedMonth: String) : DateEvent()
    data class CheckLeapYear(val year: String, val month: String) : DateEvent()
    data class UpdateSelectedDay(val selectedDay: Int) : DateEvent()
    data class SetInitialDate(val initialDay: Int? = null, val initialMonth: MonthType? = null, val initialYear: Int? = null, val yearRange: IntRange): DateEvent()
}